package com.petmatch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species; // e.g., Perro, Gato
    private String breed;
    private int age;
    private String description;
    private String photoUrl;

    // Nuevos campos para una descripción más detallada
    private String size; // e.g., Pequeño, Mediano, Grande
    private String gender; // e.g., Macho, Hembra
    private String energyLevel; // e.g., Bajo, Moderado, Alto
    private String temperament; // e.g., Juguetón, Tranquilo, Sociable, Tímido

    private boolean compatibleWithDogs;
    private boolean compatibleWithCats;
    private boolean compatibleWithChildren;

    private String specialNeeds; // Cualquier necesidad especial (dieta, medicación, etc.)
    private String trainingLevel; // e.g., Básico, Avanzado, Necesita entrenamiento

    private boolean isVaccinated;
    private boolean isDewormed;
    private boolean isSterilized;

    private String history; // Breve historia o antecedentes de la mascota

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
