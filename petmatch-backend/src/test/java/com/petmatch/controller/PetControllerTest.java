package com.petmatch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petmatch.dto.PetDto;
import com.petmatch.service.PetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PetService petService;

    @Test
    @WithMockUser // Simula un usuario autenticado para esta prueba
    void whenCreatePet_withValidData_thenReturns201AndPetDto() throws Exception {
        // 1. Datos de prueba
        PetDto petToCreate = new PetDto();
        petToCreate.setName("Fido");
        petToCreate.setType("Perro");
        // ... (puedes añadir más campos si son obligatorios)

        PetDto createdPet = new PetDto();
        createdPet.setId(1L);
        createdPet.setName("Fido");
        createdPet.setType("Perro");

        // 2. Simular el comportamiento del servicio
        // Cuando se llame a petService.createPet con cualquier PetDto, devuelve nuestro objeto "createdPet"
        when(petService.createPet(any(PetDto.class))).thenReturn(createdPet);

        // 3. Ejecutar la petición y verificar el resultado
        mockMvc.perform(post("/api/pets")
                        .with(csrf()) // Añadir token CSRF para peticiones POST seguras
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petToCreate)))
                .andExpect(status().isCreated()) // Esperamos un 201 Created
                .andExpect(jsonPath("$.id").value(1L)) // Verificamos que el JSON de respuesta tenga los datos correctos
                .andExpect(jsonPath("$.name").value("Fido"));
    }

    @Test
    @WithMockUser
    void whenCreatePet_withInvalidData_thenReturns400() throws Exception {
        // Datos inválidos (por ejemplo, nombre nulo)
        PetDto petToCreate = new PetDto();
        petToCreate.setType("Perro"); // El nombre es requerido pero no lo enviamos

        mockMvc.perform(post("/api/pets")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petToCreate)))
                .andExpect(status().isBadRequest()); // Esperamos un 400 Bad Request
    }

    @Test
    void whenCreatePet_withoutAuthentication_thenReturns401() throws Exception {
        PetDto petToCreate = new PetDto();
        petToCreate.setName("Fido");

        mockMvc.perform(post("/api/pets")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petToCreate)))
                .andExpect(status().isUnauthorized()); // Esperamos un 401 Unauthorized
    }
}
