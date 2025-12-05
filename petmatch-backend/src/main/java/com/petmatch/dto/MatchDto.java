package com.petmatch.dto;

import lombok.Data;

@Data
public class MatchDto {
    private Long id;
    private PetDto petA;
    private PetDto petB;
}
