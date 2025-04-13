package dev.java10x.elifoot.service;

import dev.java10x.elifoot.controller.response.PlayerDetailResponse;
import dev.java10x.elifoot.controller.response.PlayerResponse;
import dev.java10x.elifoot.mapper.PlayerMapper;
import dev.java10x.elifoot.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FindPlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public Page<PlayerResponse> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable)
                .map(playerMapper::toResponse);
    }

    public PlayerDetailResponse findById(Long id) {
        return playerRepository.findById(id)
                .map(playerMapper::toDetailResponse)
                .orElse(null);
    }

    public List<PlayerResponse> findByClubId(Long clubId) {
        return playerRepository.findByClubId(clubId)
                .stream()
                .map(playerMapper::toResponse)
                .toList();
    }
}
