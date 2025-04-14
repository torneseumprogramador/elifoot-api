package dev.java10x.elifoot.controller;

import dev.java10x.elifoot.controller.request.LoginRequest;
import dev.java10x.elifoot.controller.response.LoginResponse;
import dev.java10x.elifoot.service.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final FindUserService findUserService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {
        return findUserService.login(request);
    }

}
