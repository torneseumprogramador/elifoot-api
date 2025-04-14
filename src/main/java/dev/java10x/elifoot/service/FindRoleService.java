package dev.java10x.elifoot.service;

import dev.java10x.elifoot.entity.Role;
import dev.java10x.elifoot.exception.ResourceNotFoundException;
import dev.java10x.elifoot.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindRoleService {

    private final RoleRepository roleRepository;

    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for id: " + id));
    }
}
