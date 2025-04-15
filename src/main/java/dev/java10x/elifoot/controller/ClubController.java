package dev.java10x.elifoot.controller;

import dev.java10x.elifoot.controller.request.CreateClubRequest;
import dev.java10x.elifoot.controller.response.ClubDetailResponse;
import dev.java10x.elifoot.controller.response.ClubResponse;
import dev.java10x.elifoot.controller.response.PlayerResponse;
import dev.java10x.elifoot.mapper.ClubMapper;
import dev.java10x.elifoot.service.CreateClubService;
import dev.java10x.elifoot.service.FindClubService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final FindClubService findClubService;
    private final CreateClubService createClubService;
    private final ClubMapper clubMapper;

    @PreAuthorize("hasAnyAuthority('SCOPE_club:read', 'SCOPE_admin:all')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClubResponse> list(Pageable pageable) {
        return findClubService.findAll(pageable);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_club:read', 'SCOPE_admin:all')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClubDetailResponse get(@PathVariable Long id) {
        return clubMapper.toDetailResponse(findClubService.findById(id));
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_club:read', 'SCOPE_admin:all')")
    @GetMapping("/{id}/players")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerResponse> getPlayersByClub(@PathVariable Long id) {
        return findClubService.findByClubId(id);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_club:write', 'SCOPE_admin:all')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClubResponse create(@Valid @RequestBody CreateClubRequest request) {
        return createClubService.execute(request);
    }

}
