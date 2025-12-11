package com.petmatch.controller;

import com.petmatch.dto.SwipeRequest;
import com.petmatch.model.User;
import com.petmatch.repository.UserRepository;
import com.petmatch.service.SwipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/swipes")
@RequiredArgsConstructor
public class SwipeController {

    private final SwipeService swipeService;
    private final UserRepository userRepository; // Inyectar para buscar al usuario

    @PostMapping
    public ResponseEntity<?> swipe(@RequestBody SwipeRequest r) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User currentUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en la base de datos"));

        boolean matched = swipeService.swipe(currentUser.getId(), r.getPetId(), r.getType());
        return ResponseEntity.ok().body("{\"matched\":" + matched + "}");
    }
}
