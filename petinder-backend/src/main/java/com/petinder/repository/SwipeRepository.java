package com.petinder.repository;

import com.petinder.model.Swipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface SwipeRepository extends JpaRepository<Swipe, Long> {
    Optional<Swipe> findByUserIdAndPetId(Long userId, Long petId);
    List<Swipe> findAllByUserId(Long userId);
}
