package br.com.apifgc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apifgc.model.game.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
