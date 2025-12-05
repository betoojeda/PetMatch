package com.petmatch.repository;

import com.petmatch.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByMatchIdOrderByCreatedAtAsc(Long matchId);
}