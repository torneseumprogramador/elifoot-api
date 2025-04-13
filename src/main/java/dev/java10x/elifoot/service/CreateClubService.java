package dev.java10x.elifoot.service;

import dev.java10x.elifoot.controller.request.CreateClubRequest;
import dev.java10x.elifoot.controller.response.ClubResponse;
import dev.java10x.elifoot.entity.Club;
import dev.java10x.elifoot.mapper.ClubMapper;
import dev.java10x.elifoot.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateClubService {

    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;

    public ClubResponse execute(CreateClubRequest request) {
        Club club = clubRepository.save(clubMapper.toEntity(request));
        return clubMapper.toResponse(club);
    }
}
