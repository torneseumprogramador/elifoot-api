package dev.java10x.elifoot.repository;

import dev.java10x.elifoot.entity.Scopes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScopeRepository extends JpaRepository<Scopes, Long> {
}
