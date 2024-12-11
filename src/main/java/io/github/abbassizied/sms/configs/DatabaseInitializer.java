package io.github.abbassizied.sms.configs;

import io.github.abbassizied.sms.entities.Role;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.entities.Privilege;
import io.github.abbassizied.sms.enums.EPrivilege;
import io.github.abbassizied.sms.repositories.RoleRepository;
import io.github.abbassizied.sms.repositories.UserRepository;
import io.github.abbassizied.sms.repositories.PrivilegeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        initializePrivileges();
        initializeSuperAdminRoleAndPrivileges();
        initializeDefaultRole(); // Adding default role initialization
    }

    private void initializePrivileges() {
        // Ensure all EPrivilege enum values exist in the database
        for (EPrivilege privilege : EPrivilege.values()) {
            privilegeRepository.findByName(privilege).orElseGet(() -> {
                Privilege newPrivilege = new Privilege(privilege);
                return privilegeRepository.save(newPrivilege);
            });
        }
    }

    private void initializeDefaultRole() {
        roleRepository.findByName("ROLE_USER").orElseGet(() -> {
            Role defaultRole = new Role("ROLE_USER");
            return roleRepository.save(defaultRole);
        });
    }
      
    private void initializeSuperAdminRoleAndPrivileges() {
        // 1. Create SUPERADMIN Role if it doesn't exist
        Role superAdminRole = roleRepository.findByName("ROLE_SUPER_ADMIN")
                .orElseGet(() -> {
                    Role role = new Role("ROLE_SUPER_ADMIN");
                    roleRepository.save(role);
                    return role;
                });

        // 2. Assign all privileges to SUPERADMIN Role
        List<Privilege> allPrivileges = privilegeRepository.findAll();
        superAdminRole.setPrivileges(new HashSet<>(allPrivileges));
        roleRepository.save(superAdminRole);

        // 3. Create SUPERADMIN user if it doesn't exist
        Optional<User> existingSuperAdmin = userRepository.findByEmail("abbassizied@outlook.fr");
        if (existingSuperAdmin.isEmpty()) {
            User superAdmin = new User();
            superAdmin.setFirstName("Zied");
            superAdmin.setLastName("Abbassi");
            superAdmin.setEmail("abbassizied@outlook.fr");
            superAdmin.setPassword(passwordEncoder.encode("password")); // Use a secure password
            superAdmin.setActive(true);

            Set<Role> roles = new HashSet<>();
            roles.add(superAdminRole);
            superAdmin.setRoles(roles);

            userRepository.save(superAdmin);
        }
    }
}
