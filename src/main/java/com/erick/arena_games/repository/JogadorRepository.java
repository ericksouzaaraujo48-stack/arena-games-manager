package com.erick.arena_games.repository;
import com.erick.arena_games.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador,Long> {
}
