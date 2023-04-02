package br.com.apifgc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apifgc.model.Guide;

public interface GuideRepository extends JpaRepository<Guide, Long> {

}
