package com.petmatch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name; // Nombre
    private String lastName; // Apellido

    private String gender; // Género
    private Integer numberOfPets; // Cuántos perros tiene

    @Lob // Para textos largos
    private String profileDescription; // Descripción para su perfil

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder.Default
    private Instant createdAt = Instant.now();

    public enum Role {
        USER,
        ADMIN
    }
}
