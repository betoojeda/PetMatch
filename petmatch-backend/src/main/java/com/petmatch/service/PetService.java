package com.petmatch.service;

import com.petmatch.dto.PetDto;

import java.util.List;

public interface PetService {

    PetDto createPet(PetDto petDto);

    PetDto updatePet(Long id, PetDto petDto);

    void deletePet(Long id);

    PetDto getPet(Long id);

    List<PetDto> getAllPets();

    List<PetDto> getPetsByOwner(Long ownerId);
}
