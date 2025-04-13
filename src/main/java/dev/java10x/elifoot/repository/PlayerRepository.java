package dev.java10x.elifoot.repository;

import dev.java10x.elifoot.entity.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @EntityGraph(attributePaths = "club")
    List<Player> findByClubId(Long clubId);
}
