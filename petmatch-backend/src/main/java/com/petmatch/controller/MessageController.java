package com.petmatch.controller;

import com.petmatch.dto.MessageDto;
import com.petmatch.model.Message;
import com.petmatch.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> send(@RequestBody MessageDto dto) {
        Message m = Message.builder()
                .matchId(dto.getMatchId())
                .senderUserId(dto.getSenderUserId())
                .text(dto.getText())
                .build();
        return ResponseEntity.ok(messageService.send(m));
    }

    @GetMapping("/match/{matchId}")
    public ResponseEntity<List<Message>> history(@PathVariable Long matchId) {
        return ResponseEntity.ok(messageService.history(matchId));
    }
}
