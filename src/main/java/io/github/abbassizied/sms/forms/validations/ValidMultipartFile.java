package io.github.abbassizied.sms.forms.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MultipartFileValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMultipartFile {
	String message() default "{error.file.invalidType}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
