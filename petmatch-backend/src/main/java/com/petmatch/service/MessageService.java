package com.petmatch.service;

import com.petmatch.model.Message;
import com.petmatch.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Message send(Message m) {
        return messageRepository.save(m);
    }

    public List<Message> history(Long matchId) {
        return messageRepository.findAllByMatchIdOrderByCreatedAtAsc(matchId);
    }
}
