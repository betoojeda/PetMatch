package com.petinder.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "messages")
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="match_id", nullable = false)
    private Long matchId;

    @Column(name="sender_user_id", nullable = false)
    private Long senderUserId;

    @Column(columnDefinition = "text")
    private String text;

    private Instant createdAt = Instant.now();
}
