package com.petmatch.service.impl;

import com.petmatch.dto.PetDto;
import com.petmatch.mapper.PetMapper;
import com.petmatch.model.Pet;
import com.petmatch.model.User;
import com.petmatch.repository.PetRepository;
import com.petmatch.repository.UserRepository;
import com.petmatch.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final PetMapper petMapper;

    @Override
    public PetDto createPet(PetDto dto) {
        User owner = userRepository.findById(1L) // FIXME: get owner from authenticated user
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        Pet pet = Pet.builder()
                .name(dto.getName())
                .species(dto.getType())
                .age(dto.getAge())
                .owner(owner)
                .build();
        return petMapper.toDto(petRepository.save(pet));
    }

    @Override
    public PetDto updatePet(Long id, PetDto petDto) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        pet.setName(petDto.getName());
        pet.setSpecies(petDto.getType());
        pet.setAge(petDto.getAge());
        return petMapper.toDto(petRepository.save(pet));
    }

    @Override
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public PetDto getPet(Long id) {
        return petRepository.findById(id)
                .map(petMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    @Override
    public List<PetDto> getAllPets() {
        return petRepository.findAll().stream()
                .map(petMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDto> getPetsByOwner(Long ownerId) {
        // This method is not yet implemented in the repository
        return List.of();
    }

    @Service
    @RequiredArgsConstructor
    public static class PetServiceImpl implements PetService {

        private final PetRepository petRepository;
        private final UserRepository userRepository;
        private final PetMapper petMapper;

        @Override
        public PetDto createPet(PetDto dto) {
            User owner = userRepository.findById(1L) // FIXME: get owner from authenticated user
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            Pet pet = Pet.builder()
                    .name(dto.getName())
                    .species(dto.getType())
                    .age(dto.getAge())
                    .owner(owner)
                    .build();
            return petMapper.toDto(petRepository.save(pet));
        }

        @Override
        public PetDto updatePet(Long id, PetDto petDto) {
            Pet pet = petRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pet not found"));
            pet.setName(petDto.getName());
            pet.setSpecies(petDto.getType());
            pet.setAge(petDto.getAge());
            return petMapper.toDto(petRepository.save(pet));
        }

        @Override
        public void deletePet(Long id) {
            petRepository.deleteById(id);
        }

        @Override
        public PetDto getPet(Long id) {
            return petRepository.findById(id)
                    .map(petMapper::toDto)
                    .orElseThrow(() -> new RuntimeException("Pet not found"));
        }

        @Override
        public List<PetDto> getAllPets() {
            return petRepository.findAll().stream()
                    .map(petMapper::toDto)
                    .collect(Collectors.toList());
        }

        @Override
        public List<PetDto> getPetsByOwner(Long ownerId) {
            // This method is not yet implemented in the repository
            return List.of();
        }
    }
}
