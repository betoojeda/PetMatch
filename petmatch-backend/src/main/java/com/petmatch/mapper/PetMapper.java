package com.petmatch.mapper;

import com.petmatch.dto.PetDto;
import com.petmatch.model.Pet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetMapper {

    public PetDto toDto(Pet pet) {
        PetDto dto = new PetDto();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setType(pet.getSpecies());
        dto.setBreed(pet.getBreed());
        dto.setAge(pet.getAge());
        dto.setDescription(pet.getDescription());
        dto.setPhotoUrl(pet.getPhotoUrl());

        // Mapeo de los nuevos campos
        dto.setSize(pet.getSize());
        dto.setGender(pet.getGender());
        dto.setEnergyLevel(pet.getEnergyLevel());
        dto.setTemperament(pet.getTemperament());
        dto.setCompatibleWithDogs(pet.isCompatibleWithDogs());
        dto.setCompatibleWithCats(pet.isCompatibleWithCats());
        dto.setCompatibleWithChildren(pet.isCompatibleWithChildren());
        dto.setSpecialNeeds(pet.getSpecialNeeds());
        dto.setTrainingLevel(pet.getTrainingLevel());
        dto.setVaccinated(pet.isVaccinated());
        dto.setDewormed(pet.isDewormed());
        dto.setSterilized(pet.isSterilized());
        dto.setHistory(pet.getHistory());

        return dto;
    }

    public List<PetDto> toDtoList(List<Pet> pets) {
        return pets.stream().map(this::toDto).toList();
    }
}
