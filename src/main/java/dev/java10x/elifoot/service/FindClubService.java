package dev.java10x.elifoot.service;

import dev.java10x.elifoot.controller.response.ClubResponse;
import dev.java10x.elifoot.controller.response.PlayerResponse;
import dev.java10x.elifoot.entity.Club;
import dev.java10x.elifoot.exception.ResourceNotFoundException;
import dev.java10x.elifoot.mapper.ClubMapper;
import dev.java10x.elifoot.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindClubService {

    private final FindPlayerService findPlayerService;

    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;

    public Page<ClubResponse> findAll(Pageable pageable) {
        return clubRepository.findAll(pageable)
                .map(clubMapper::toResponse);
    }

    public Club findById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Club not found for id: " + id));
    }

    public List<PlayerResponse> findByClubId(Long clubId) {
        return findPlayerService.findByClubId(clubId);
    }
}
