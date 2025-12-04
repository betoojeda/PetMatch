package com.petinder.repository;

import com.petinder.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("select p from Pet p where p.id not in ?1")
    List<Pet> findByIdNotIn(List<Long> excludedIds);
}
