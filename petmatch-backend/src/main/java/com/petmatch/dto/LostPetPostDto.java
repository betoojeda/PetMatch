package com.petmatch.dto;

import com.petmatch.model.LostPetPost;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LostPetPostDto {
    private Long id;
    private String petName;
    private String description;
    private String photoUrl;
    private String location;
    private LostPetPost.PostStatus status;
    private LocalDateTime createdAt;
    private UserDto user; // Usamos UserDto para no exponer datos sensibles
    private List<CommentDto> comments;
}
