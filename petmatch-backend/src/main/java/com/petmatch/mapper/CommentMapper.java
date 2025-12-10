package com.petmatch.mapper;

import com.petmatch.dto.CommentDto;
import com.petmatch.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CommentMapper {

    @Mapping(source = "user", target = "user")
    CommentDto toDto(Comment comment);

    @Mapping(target = "lostPetPost", ignore = true) // Ignorar para evitar ciclos
    Comment toEntity(CommentDto commentDto);
}
