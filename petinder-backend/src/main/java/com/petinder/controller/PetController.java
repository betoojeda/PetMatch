package com.petinder.controller;

import com.petinder.dto.PetDto;
import com.petinder.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public PetDto create(@RequestBody PetDto pet) {
        return petService.createPet(pet);
    }

    @PutMapping("/{id}")
    public PetDto update(@PathVariable Long id, @RequestBody PetDto pet) {
        return petService.updatePet(id, pet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        petService.deletePet(id);
    }

    @GetMapping("/{id}")
    public PetDto get(@PathVariable Long id) {
        return petService.getPet(id);
    }

    @GetMapping
    public List<PetDto> getAll() {
        return petService.getAllPets();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDto> byOwner(@PathVariable Long ownerId) {
        return petService.getPetsByOwner(ownerId);
    }
}
