package com.petmatch.service.impl;

import com.petmatch.dto.MatchDto;
import com.petmatch.mapper.PetMapper;
import com.petmatch.model.Pet;
import com.petmatch.repository.MatchRepository;
import com.petmatch.repository.PetRepository;
import com.petmatch.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private static final Logger log = LoggerFactory.getLogger(MatchServiceImpl.class);

    private final MatchRepository matchRepository;
    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Override
    public List<MatchDto> getMatchesForUser(Long userId) {
        log.info("Fetching matches for user with ID: {}", userId);
        // 1. Encontrar todas las mascotas del usuario
        List<Pet> userPets = petRepository.findByOwnerId(userId);
        List<Long> userPetIds = userPets.stream().map(Pet::getId).collect(Collectors.toList());
        log.debug("User with ID: {} has {} pets", userId, userPetIds.size());

        // 2. Encontrar todos los matches donde participa alguna de sus mascotas
        // Usamos un Stream para evitar duplicados si un match es entre dos mascotas del mismo usuario
        List<MatchDto> matches = matchRepository.findAllByPetAInOrPetBIn(userPetIds, userPetIds).stream()
                .distinct()
                .map(matchEntity -> {
                    // 3. Cargar los datos completos de ambas mascotas
                    Pet petA = petRepository.findById(matchEntity.getPetA()).orElse(null);
                    Pet petB = petRepository.findById(matchEntity.getPetB()).orElse(null);

                    // 4. Construir y devolver el DTO
                    if (petA == null || petB == null) {
                        log.warn("Match with ID: {} references a non-existent pet. Skipping.", matchEntity.getId());
                        return null;
                    }

                    MatchDto dto = new MatchDto();
                    dto.setId(matchEntity.getId());
                    dto.setPetA(petMapper.toDto(petA));
                    dto.setPetB(petMapper.toDto(petB));
                    return dto;
                })
                .filter(dto -> dto != null)
                .collect(Collectors.toList());
        log.info("Found {} matches for user with ID: {}", matches.size(), userId);
        return matches;
    }
}
