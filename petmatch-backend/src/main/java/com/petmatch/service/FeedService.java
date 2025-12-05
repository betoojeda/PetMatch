package com.petmatch.service;

import com.petmatch.dto.PetDto;
import com.petmatch.mapper.PetMapper;
import com.petmatch.model.Pet;
import com.petmatch.repository.PetRepository;
import com.petmatch.repository.SwipeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FeedService {

    private static final Logger log = LoggerFactory.getLogger(FeedService.class);

    private final PetRepository petRepository;
    private final SwipeRepository swipeRepository;
    private final PetMapper petMapper;

    public Page<PetDto> getFeed(Long userId, int page, int size) {
        log.info("Fetching feed for user with ID: {}, page: {}, size: {}", userId, page, size);

        // mascotas que el usuario ya swapeó
        Set<Long> swipedPetIds = swipeRepository.findAllSwipedPetIdsByUser(userId);
        log.debug("User with ID: {} has swiped on {} pets", userId, swipedPetIds.size());

        if (swipedPetIds.isEmpty()) {
            // evita error en SQL con "NOT IN ()"
            swipedPetIds = Set.of(-1L);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Pet> pets = petRepository.findAvailableFeed(userId, swipedPetIds, pageable);
        log.info("Found {} pets for feed for user with ID: {}", pets.getTotalElements(), userId);

        return pets.map(petMapper::toDto);
    }
}
