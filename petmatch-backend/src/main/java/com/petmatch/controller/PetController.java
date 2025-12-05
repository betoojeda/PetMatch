package com.petmatch.controller;

import com.petmatch.dto.PetDto;
import com.petmatch.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    @PreAuthorize("isAuthenticated()") // Solo usuarios logueados pueden crear mascotas
    public PetDto create(@Valid @RequestBody PetDto pet) {
        return petService.createPet(pet);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public PetDto update(@PathVariable Long id, @Valid @RequestBody PetDto pet) {
        return petService.updatePet(id, pet);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Solo admins pueden borrar
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
