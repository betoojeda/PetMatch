package com.petmatch.service.impl;

import com.petmatch.model.Match;
import com.petmatch.model.Pet;
import com.petmatch.model.Swipe;
import com.petmatch.model.User;
import com.petmatch.repository.MatchRepository;
import com.petmatch.repository.PetRepository;
import com.petmatch.repository.SwipeRepository;
import com.petmatch.repository.UserRepository;
import com.petmatch.service.SwipeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SwipeServiceImpl implements SwipeService {

    private static final Logger log = LoggerFactory.getLogger(SwipeServiceImpl.class);

    private final SwipeRepository swipeRepository;
    private final PetRepository petRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public boolean swipe(Long userId, Long petId, String type) {
        log.info("User with ID: {} swiped on pet with ID: {} with type: {}", userId, petId, type);
        Optional<Swipe> existing = swipeRepository.findByUserIdAndPetId(userId, petId);
        if (existing.isPresent()) {
            log.warn("User with ID: {} already swiped on pet with ID: {}. Ignoring.", userId, petId);
            return false;
        }

        Swipe swipe = Swipe.builder()
                .userId(userId)
                .petId(petId)
                .type(type)
                .build();
        swipeRepository.save(swipe);
        log.debug("Swipe saved for user with ID: {} and pet with ID: {}", userId, petId);

        if (!"LIKE".equalsIgnoreCase(type)) {
            return false;
        }

        // Simple reciprocity logic: if the pet owner swipes one of your pets with LIKE, make a match.
        Pet targetPet = petRepository.findById(petId).orElse(null);
        if (targetPet == null) {
            log.error("Target pet with ID: {} not found for swipe.", petId);
            return false;
        }

        // Check if owner of targetPet (owner B) liked any of user A's pets
        User ownerOfTarget = targetPet.getOwner();
        User userA = userRepository.findById(userId).orElse(null);
        if (ownerOfTarget == null || userA == null) {
            log.error("Owner of target pet or user A not found.");
            return false;
        }

        // Find pets of userA and check if ownerOfTarget swiped any of them with LIKE
        for (Pet petOfA : petRepository.findAll()) {
            if (petOfA.getOwner() == null) continue;
            if (!petOfA.getOwner().getId().equals(userA.getId())) continue;

            Optional<Swipe> s = swipeRepository.findByUserIdAndPetId(ownerOfTarget.getId(), petOfA.getId());
            if (s.isPresent() && "LIKE".equalsIgnoreCase(s.get().getType())) {
                // create match between petOfA.id and targetPet.id
                Long aId = petOfA.getId();
                Long bId = targetPet.getId();
                // Normalize order for uniqueness (min, max)
                Long petA = Math.min(aId, bId);
                Long petB = Math.max(aId, bId);
                if (matchRepository.findByPetAAndPetB(petA, petB).isEmpty()) {
                    Match m = Match.builder().petA(petA).petB(petB).build();
                    matchRepository.save(m);
                    log.info("Match created between pet with ID: {} and pet with ID: {}", petA, petB);
                }
                return true;
            }
        }
        return false;
    }
}
