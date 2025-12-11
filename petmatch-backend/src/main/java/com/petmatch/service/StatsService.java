package com.petmatch.service;

import com.petmatch.dto.PublicStatsDto;
import com.petmatch.model.User;
import com.petmatch.repository.MatchRepository;
import com.petmatch.repository.PetRepository;
import com.petmatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final MatchRepository matchRepository;

    public PublicStatsDto getPublicStats() {
        return PublicStatsDto.builder()
                .totalUsers(userRepository.countByRoleNot(User.Role.ADMIN)) // Usar el nuevo método
                .totalPets(petRepository.count())
                .totalMatches(matchRepository.count())
                .build();
    }
}
