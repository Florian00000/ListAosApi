package com.florian.aos.securityservice.config;

import com.florian.aos.securityservice.entity.Role;
import com.florian.aos.securityservice.entity.User;
import com.florian.aos.securityservice.repository.RoleRepository;
import com.florian.aos.securityservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Configuration
public class DataInitializer {

    @Value("${app.admin.email:admin@admin.com}")
    private String adminEmail;

    @Value("${app.admin.password:admin123}")
    private String adminPassword;

    @Value("${app.admin.firstName:Admin}")
    private String adminFirstName;

    @Value("${app.admin.lastName:System}")
    private String adminLastName;

    @Value("${app.init.enabled:true}")
    private boolean initEnabled;

    @Bean
    @Transactional
    public CommandLineRunner initDatabase(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            if (!initEnabled) {
                log.info("Database initialization is disabled");
                return;
            }

            log.info("Starting database initialization...");

            // Créer les rôles par défaut s'ils n'existent pas
            Role roleUser = createRoleIfNotExists(roleRepository, "ROLE_USER");
            Role roleAdmin = createRoleIfNotExists(roleRepository, "ROLE_ADMIN");

            // Créer l'utilisateur admin par défaut s'il n'existe pas
            createAdminUserIfNotExists(
                    userRepository,
                    passwordEncoder,
                    roleUser,
                    roleAdmin
            );

            log.info("Database initialization completed successfully");
        };
    }

    private Role createRoleIfNotExists(RoleRepository roleRepository, String roleName) {
        return roleRepository.findByRole(roleName)
                .orElseGet(() -> {
                    log.info("Creating role: {}", roleName);
                    Role role = Role.builder()
                            .role(roleName)
                            .build();
                    Role savedRole = roleRepository.save(role);
                    log.info("Role created successfully: {}", roleName);
                    return savedRole;
                });
    }

    private void createAdminUserIfNotExists(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            Role roleUser,
            Role roleAdmin) {

        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            log.info("Creating default admin user with email: {}", adminEmail);

            User adminUser = User.builder()
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .firstName(adminFirstName)
                    .lastName(adminLastName)
                    .roles(List.of(roleUser, roleAdmin))
                    .build();

            userRepository.save(adminUser);
            log.warn("========================================");
            log.warn("Default admin user created!");
            log.warn("Email: {}", adminEmail);
            log.warn("Password: {}", adminPassword);
            log.warn("PLEASE CHANGE THE PASSWORD IN PRODUCTION!");
            log.warn("========================================");
        } else {
            log.info("Admin user already exists with email: {}, skipping creation", adminEmail);
        }
    }
}
