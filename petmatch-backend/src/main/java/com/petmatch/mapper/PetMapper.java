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
        dto.setAge(pet.getAge());
        dto.setPhotoUrl(null); // Pet model does not have a photoUrl
        return dto;
    }

    public List<PetDto> toDtoList(List<Pet> pets) {
        return pets.stream().map(this::toDto).toList();
    }
}
