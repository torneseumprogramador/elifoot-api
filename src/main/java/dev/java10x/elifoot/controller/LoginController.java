package dev.java10x.elifoot.controller;

import dev.java10x.elifoot.controller.request.LoginRequest;
import dev.java10x.elifoot.controller.response.LoginResponse;
import dev.java10x.elifoot.entity.Role;
import dev.java10x.elifoot.entity.User;
import dev.java10x.elifoot.service.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final FindUserService findUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Optional<User> optUser = findUserService.findByEmail(request.getEmail());

        if (optUser.isEmpty() || !isPasswordCorrect(request.getPassword(), optUser.get().getPassword())) {
            throw new BadCredentialsException("Usuário ou senha incorretos!");
        }

        User usuario = optUser.get();
        List<String> roles = usuario.getRoles().stream()
                .map(Role::getName)
                .toList();
        long expiresIn = 600L;

        JwtClaimsSet jwt = JwtClaimsSet.builder()
                .issuer("elifoot-api")
                .subject(usuario.getName())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .issuedAt(Instant.now())
                .claim("email", usuario.getEmail())
                .claim("scope", roles)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(jwt)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(token, expiresIn));
    }

    private boolean isPasswordCorrect(String password, String savedPassowrd) {
        return passwordEncoder.matches(password, savedPassowrd);
    }
}
