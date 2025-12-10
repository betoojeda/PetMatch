package com.petmatch.mapper;

import com.petmatch.dto.LostPetPostDto;
import com.petmatch.model.LostPetPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CommentMapper.class})
public interface LostPetPostMapper {

    @Mapping(source = "user", target = "user")
    @Mapping(target = "comments", ignore = true) // Ignorar para romper el ciclo
    LostPetPostDto toDto(LostPetPost lostPetPost);

    LostPetPost toEntity(LostPetPostDto lostPetPostDto);
}
