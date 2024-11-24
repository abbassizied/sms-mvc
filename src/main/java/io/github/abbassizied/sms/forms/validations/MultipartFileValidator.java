package io.github.abbassizied.sms.forms.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import io.github.abbassizied.sms.configs.FileProperties;

@Component
public class MultipartFileValidator implements ConstraintValidator<ValidMultipartFile, MultipartFile> {

	private final MessageSource messageSource;
	private final FileProperties fileProperties;

	// Constructor injection
	public MultipartFileValidator(MessageSource messageSource, FileProperties fileProperties) {
		this.messageSource = messageSource;
		this.fileProperties = fileProperties;
	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		if (file == null || file.isEmpty()) {
			context.disableDefaultConstraintViolation();
			String emptyFileMessage = messageSource.getMessage("error.file.empty",
					new Object[] { String.join(", ", fileProperties.getExtensions()) }, // Inject allowedExtensions
					Locale.getDefault());
			context.buildConstraintViolationWithTemplate(emptyFileMessage).addConstraintViolation();
			return false;
		}
		// Continue with the other validations (e.g., extensions, MIME type)
		String fileName = file.getOriginalFilename();
		String contentType = file.getContentType();

		// Validate file extension
		if (fileName != null && Arrays.stream(fileProperties.getExtensions()).noneMatch(fileName::endsWith)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(messageSource.getMessage("error.file.invalidType",
					new Object[] { String.join(", ", fileProperties.getExtensions()) }, Locale.getDefault()))
					.addConstraintViolation();
			return false;
		}

		// Validate MIME type
		if (contentType != null
				&& Arrays.stream(fileProperties.getMimeTypes()).noneMatch(contentType::equalsIgnoreCase)) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(messageSource.getMessage("error.file.invalidType",
					new Object[] { String.join(", ", fileProperties.getMimeTypes()) }, Locale.getDefault()))
					.addConstraintViolation();
			return false;
		}

		return true;
	}
}
