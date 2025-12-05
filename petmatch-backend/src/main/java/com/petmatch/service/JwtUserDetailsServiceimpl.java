package com.petmatch.service;

import com.petmatch.model.User;
import com.petmatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceimpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(JwtUserDetailsServiceimpl.class);

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Loading user by username: {}", username);
        User u = userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    log.error("User not found with username: {}", username);
                    return new UsernameNotFoundException("Usuario no encontrado: " + username);
                });

        log.debug("User found by username: {}", username);
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), Collections.emptyList());
    }
}
