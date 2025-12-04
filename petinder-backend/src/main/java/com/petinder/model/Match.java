package com.petinder.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pet_a","pet_b"})
})
public class Match {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pet_a", nullable = false)
    private Long petA;

    @Column(name = "pet_b", nullable = false)
    private Long petB;

    private Instant createdAt = Instant.now();
}
