package com.petmatch.service;

import com.petmatch.dto.PetDto;
import com.petmatch.dto.UserAdminDto;

import java.util.List;

public interface AdminService {
    List<UserAdminDto> getAllUsers();
    List<PetDto> getAllPets();
}
