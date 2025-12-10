package com.petmatch.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lost_pet_posts")
@Data
public class LostPetPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String petName;
    
    @Column(columnDefinition = "TEXT")
    private String description;

    private String photoUrl;

    private String location; // Ciudad, Barrio o Coordenadas

    @Enumerated(EnumType.STRING)
    private PostStatus status; // LOST, FOUND, REUNITED

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // El usuario que publica el aviso

    @OneToMany(mappedBy = "lostPetPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public enum PostStatus {
        LOST,       // Perdido
        FOUND,      // Encontrado (alguien encontró un perro y busca al dueño)
        REUNITED    // Reunido (caso cerrado)
    }
}
