package io.github.abbassizied.sms.forms.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class NotEmptyPrivilegesValidator implements ConstraintValidator<NotEmptyPrivileges, Set<?>> {

    @Override
    public void initialize(NotEmptyPrivileges constraintAnnotation) {
        // Initialization logic (if any)
    }

    @Override
    public boolean isValid(Set<?> value, ConstraintValidatorContext context) {
        // Check if the set is null or empty
        return value != null && !value.isEmpty();
    }
}
