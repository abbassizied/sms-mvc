package io.github.abbassizied.sms.forms.validations;


import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueRoleNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueRoleName {

    String message() default "Role with this name already exists."; // Default error message

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}