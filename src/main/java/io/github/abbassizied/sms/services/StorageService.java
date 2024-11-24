package io.github.abbassizied.sms.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
	 public void init();

	 public String store(MultipartFile file);

	 public Stream<Path> loadAll();

	 public Path load(String filename);

	 public Resource loadAsResource(String filename);
	 
	 public boolean delete(String filename);

	 public void deleteAll();
}