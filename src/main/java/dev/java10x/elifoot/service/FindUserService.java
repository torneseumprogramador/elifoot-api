package dev.java10x.elifoot.service;

import dev.java10x.elifoot.controller.request.LoginRequest;
import dev.java10x.elifoot.controller.response.LoginResponse;
import dev.java10x.elifoot.entity.Scopes;
import dev.java10x.elifoot.entity.User;
import dev.java10x.elifoot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginResponse login(LoginRequest request) {
        Optional<User> optUser = userRepository.findByEmail(request.getEmail());

        if (optUser.isEmpty() || !isPasswordCorrect(request.getPassword(), optUser.get().getPassword())) {
            throw new BadCredentialsException("Usuário ou senha incorretos!");
        }

        User usuario = optUser.get();
        List<String> scopes = usuario.getScopes().stream()
                .map(Scopes::getName)
                .toList();
        long expiresIn = 600L;

        JwtClaimsSet jwt = JwtClaimsSet.builder()
                .issuer("elifoot-api")
                .subject(usuario.getName())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .issuedAt(Instant.now())
                .claim("email", usuario.getEmail())
                .claim("scope", scopes)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(jwt)).getTokenValue();

        return new LoginResponse(token, expiresIn);
    }

    private boolean isPasswordCorrect(String password, String savedPassowrd) {
        return passwordEncoder.matches(password, savedPassowrd);
    }
}
