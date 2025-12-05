package com.petmatch.service;

import com.petmatch.model.Message;
import com.petmatch.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private static final Logger log = LoggerFactory.getLogger(MessageService.class);
    private final MessageRepository messageRepository;

    public Message send(Message m) {
        log.info("Sending message from user with ID: {} to match with ID: {}", m.getSenderUserId(), m.getMatchId());
        Message savedMessage = messageRepository.save(m);
        log.info("Message sent with ID: {}", savedMessage.getId());
        return savedMessage;
    }

    public List<Message> history(Long matchId) {
        log.info("Fetching message history for match with ID: {}", matchId);
        List<Message> messages = messageRepository.findAllByMatchIdOrderByCreatedAtAsc(matchId);
        log.info("Found {} messages for match with ID: {}", messages.size(), matchId);
        return messages;
    }
}
