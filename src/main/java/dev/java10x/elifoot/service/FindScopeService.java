package dev.java10x.elifoot.service;

import dev.java10x.elifoot.entity.Scopes;
import dev.java10x.elifoot.exception.ResourceNotFoundException;
import dev.java10x.elifoot.repository.ScopeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindScopeService {

    private final ScopeRepository scopeRepository;

    public Scopes findById(Long id) {
        return scopeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scope not found for id: " + id));
    }
}
