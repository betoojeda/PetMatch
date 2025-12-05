package com.petmatch.service;

import com.petmatch.dto.MatchDto;

import java.util.List;

public interface MatchService {
    List<MatchDto> getMatchesForUser(Long userId);
}
