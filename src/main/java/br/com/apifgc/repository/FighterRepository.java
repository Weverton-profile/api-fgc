package br.com.apifgc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apifgc.model.Fighter;

public interface FighterRepository extends JpaRepository<Fighter, Long> {

}
