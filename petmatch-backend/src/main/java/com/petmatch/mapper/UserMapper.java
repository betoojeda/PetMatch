package com.petmatch.mapper;

import com.petmatch.dto.UserDto;
import com.petmatch.model.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// Desactivamos el builder para evitar conflictos con Lombok en entidades JPA complejas.
// MapStruct usará el constructor vacío y los setters.
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {

    // Mapea de User a UserDto. Los campos extra en User se ignoran automáticamente.
    UserDto toDto(User user);

    // Mapea de UserDto a User.
    // Ignoramos los campos que no están en el DTO para evitar que se pongan a null.
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "numberOfPets", ignore = true)
    @Mapping(target = "profileDescription", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(UserDto userDto);
}
