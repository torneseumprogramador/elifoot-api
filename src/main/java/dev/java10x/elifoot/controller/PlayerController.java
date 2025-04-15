package dev.java10x.elifoot.controller;

import dev.java10x.elifoot.config.security.annotations.CanReadPlayer;
import dev.java10x.elifoot.config.security.annotations.CanWritePlayer;
import dev.java10x.elifoot.controller.request.CreatePlayerRequest;
import dev.java10x.elifoot.controller.response.PlayerDetailResponse;
import dev.java10x.elifoot.controller.response.PlayerResponse;
import dev.java10x.elifoot.service.CreatePlayerService;
import dev.java10x.elifoot.service.FindPlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final FindPlayerService findPlayerService;
    private final CreatePlayerService createPlayerService;

    @CanReadPlayer
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PlayerResponse> list(Pageable pageable) {
        return findPlayerService.findAll(pageable);
    }

    @CanReadPlayer
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerDetailResponse get(@PathVariable Long id) {
        return findPlayerService.findById(id);
    }

    @CanWritePlayer
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerResponse create(@Valid @RequestBody CreatePlayerRequest request) {
        return createPlayerService.execute(request);
    }

}
