package com.petmatch.dto;

import com.petmatch.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAdminDto {
    private Long id;
    private String email;
    private String name;
    private String lastName;
    private String gender;
    private Integer numberOfPets;
    private String profileDescription;
    private User.Role role;
    private Instant createdAt;
}
