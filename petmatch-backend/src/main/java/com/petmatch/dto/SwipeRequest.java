package com.petmatch.dto;

import lombok.Data;

@Data
public class SwipeRequest {
    private Long petId;
    private String type;
}
