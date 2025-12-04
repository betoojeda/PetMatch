package com.petinder.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "swipes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","pet_id"})
})
public class Swipe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", nullable = false)
    private Long userId;

    @Column(name="pet_id", nullable = false)
    private Long petId;

    @Column(nullable = false)
    private String type; // LIKE or DISLIKE

    private Instant createdAt = Instant.now();
}
