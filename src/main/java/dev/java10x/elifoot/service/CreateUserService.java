package dev.java10x.elifoot.service;

import dev.java10x.elifoot.controller.request.CreateUserRequest;
import dev.java10x.elifoot.controller.response.UserResponse;
import dev.java10x.elifoot.entity.User;
import dev.java10x.elifoot.mapper.UserMapper;
import dev.java10x.elifoot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponse create(CreateUserRequest request) {

        // validar se as roles existem
        // validar se o email já existe

        User newUser = userMapper.toEntity(request);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userRepository.save(userMapper.toEntity(request));
        return userMapper.toResponse(user);
    }
}
