package io.github.abbassizied.sms.services; 

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
 
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils; 
import org.springframework.web.multipart.MultipartFile;

import io.github.abbassizied.sms.configs.StorageProperties;
import io.github.abbassizied.sms.exceptions.StorageException;
import io.github.abbassizied.sms.exceptions.StorageFileNotFoundException;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;
 
	public FileSystemStorageService(StorageProperties properties) {

		if (properties.getLocation().trim().length() == 0) {
			throw new StorageException("File upload location can not be Empty.");
		}

		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Override
	public String store(MultipartFile file) {
	    try {
	        if (file.isEmpty()) {
	            throw new StorageException("Failed to store empty file.");
	        }

	        // Generate a unique file name using System.currentTimeMillis() + original file name
	        String originalFileName = file.getOriginalFilename();
	        String uniqueFileName = System.currentTimeMillis() + "_" + (originalFileName != null ? originalFileName : "unnamed");

	        // Resolve the file path
	        Path destinationFile = this.rootLocation.resolve(Paths.get(uniqueFileName)).normalize().toAbsolutePath();

	        // Security check to prevent path traversal attacks
	        if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
	            throw new StorageException("Cannot store file outside current directory.");
	        }

	        // Save the file
	        try (InputStream inputStream = file.getInputStream()) {
	            Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
	        }

	        // Return the unique file name
	        return uniqueFileName;

	    } catch (IOException e) {
	        throw new StorageException("Failed to store file.", e);
	    }
	}


	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public boolean delete(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			return Files.deleteIfExists(file);
		} catch (IOException e) {
			throw new StorageException("Failed to delete file.", e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

}