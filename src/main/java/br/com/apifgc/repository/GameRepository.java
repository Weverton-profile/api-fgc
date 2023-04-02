package br.com.apifgc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apifgc.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
