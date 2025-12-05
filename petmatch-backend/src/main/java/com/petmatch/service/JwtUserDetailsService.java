package com.petmatch.service;

import com.petmatch.model.User;
import com.petmatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        log.debug("Finding user by email: {}", email);
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(u -> log.debug("User found by email: {}", email));
        return user;
    }

    public Optional<User> findById(Long id) {
        log.debug("Finding user by ID: {}", id);
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> log.debug("User found by ID: {}", id));
        return user;
    }
}
