package com.petmatch.controller;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private static final Logger log = LoggerFactory.getLogger(LogController.class);

    @PostMapping
    public ResponseEntity<Void> logError(@RequestBody LogRequest logRequest) {
        // Aquí registramos el error en los logs del servidor (que Railway captura)
        log.error("CLIENT ERROR: [{}] {} - Stack: {}", 
                logRequest.getLevel(), 
                logRequest.getMessage(), 
                logRequest.getStack());
        return ResponseEntity.ok().build();
    }

    @Data
    public static class LogRequest {
        private String level;
        private String message;
        private String stack;
    }
}
