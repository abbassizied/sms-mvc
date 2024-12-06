package io.github.abbassizied.sms.forms;

import jakarta.validation.constraints.*;
import io.github.abbassizied.sms.forms.validations.UniqueRoleName;

public class RoleForm {

    @UniqueRoleName // Custom validator to check for uniqueness
    @Pattern(regexp = "^ROLE_[A-Z]+$", message = "Role name must start with 'ROLE_' followed by uppercase letters only")
    @NotBlank(message = "Role name cannot be blank")
    private String name;

    // Constructor
    public RoleForm() {}

    public RoleForm(String name) {
        this.name = name;
    }

    // Getters and setters
    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }
}
