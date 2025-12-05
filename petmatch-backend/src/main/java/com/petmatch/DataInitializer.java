package com.petmatch;

import com.petmatch.model.User;
import com.petmatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Crear el usuario administrador si no existe
        if (userRepository.findByEmail("admin@petmatch.com").isEmpty()) {
            User adminUser = User.builder()
                    .name("ADMINISTRADOR")
                    .email("admin@petmatch.com")
                    .password(passwordEncoder.encode("admin01"))
                    .role(User.Role.ADMIN)
                    .build();
            userRepository.save(adminUser);
        }
    }
}
