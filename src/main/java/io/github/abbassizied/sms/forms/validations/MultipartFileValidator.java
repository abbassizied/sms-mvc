package io.github.abbassizied.sms.forms.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import io.github.abbassizied.sms.configs.FileProperties;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class MultipartFileValidator implements ConstraintValidator<ValidMultipartFile, Object> {

    private final MessageSource messageSource;
    private final FileProperties fileProperties;

    // Constructor injection
    public MultipartFileValidator(MessageSource messageSource, FileProperties fileProperties) {
        this.messageSource = messageSource;
        this.fileProperties = fileProperties;
    }

    @Override
    public void initialize(ValidMultipartFile constraintAnnotation) {
        // No initialization required in this case, but can be used for more complex setup if needed
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof MultipartFile) {
            // Handle the validation for a single MultipartFile
            return validateSingleFile((MultipartFile) value, context);
        } else if (value instanceof List) {
            // Handle the validation for a list of MultipartFiles
            return validateFileList((List<MultipartFile>) value, context);
        }
        return true; // Return true for unsupported object types (shouldn't happen in normal use)
    }

    private boolean validateSingleFile(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            context.disableDefaultConstraintViolation();
            String emptyFileMessage = messageSource.getMessage("error.file.empty",
                    new Object[]{String.join(", ", fileProperties.getExtensions())}, // Inject allowedExtensions
                    Locale.getDefault());
            context.buildConstraintViolationWithTemplate(emptyFileMessage).addConstraintViolation();
            return false;
        }

        // Validate file extension
        String fileName = file.getOriginalFilename();
        if (fileName != null && Arrays.stream(fileProperties.getExtensions()).noneMatch(fileName::endsWith)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(messageSource.getMessage("error.file.invalidType",
                    new Object[]{String.join(", ", fileProperties.getExtensions())}, Locale.getDefault()))
                    .addConstraintViolation();
            return false;
        }

        // Validate MIME type
        String contentType = file.getContentType();
        if (contentType != null && Arrays.stream(fileProperties.getMimeTypes()).noneMatch(contentType::equalsIgnoreCase)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(messageSource.getMessage("error.file.invalidType",
                    new Object[]{String.join(", ", fileProperties.getMimeTypes())}, Locale.getDefault()))
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean validateFileList(List<MultipartFile> files, ConstraintValidatorContext context) {
        // If there are no files, it's valid (can be customized as per requirements)
        if (files == null || files.isEmpty()) {
            return true;
        }

        // Check each file in the list for validity
        for (MultipartFile file : files) {
            if (!validateSingleFile(file, context)) {
                return false;  // Return false if any file is invalid
            }
        }
        return true;  // All files are valid
    }
}
