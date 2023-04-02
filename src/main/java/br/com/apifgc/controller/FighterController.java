package br.com.apifgc.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.apifgc.dto.fighter.FighterRegistrationAllData;
import br.com.apifgc.dto.fighter.FighterRegistrationData;
import br.com.apifgc.dto.fighter.FighterUpdateData;
import br.com.apifgc.model.Fighter;
import br.com.apifgc.model.Game;
import br.com.apifgc.repository.FighterRepository;
import br.com.apifgc.repository.GameRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("fighters")
public class FighterController {
	
	@Autowired
	private FighterRepository fighterRepository;
	@Autowired
	private GameRepository gameRepository;
	
	@GetMapping("show/{id}")
	public ResponseEntity<FighterRegistrationAllData> detail(@PathVariable Long id) {
		Fighter fighter = fighterRepository.getReferenceById(id);
		return ResponseEntity.ok(new FighterRegistrationAllData(fighter));
	}
	
	@GetMapping("list")
	public ResponseEntity<Page<FighterRegistrationData>> list(@PageableDefault(size = 10) Pageable pagination) {
		Page<FighterRegistrationData> page = fighterRepository.findAll(pagination).map(FighterRegistrationData::new);
		return ResponseEntity.ok(page);
	}
	
	@PostMapping("new")
	@Transactional
	public ResponseEntity<FighterRegistrationData> register(@RequestBody @Valid FighterRegistrationData data, UriComponentsBuilder uriBuilder) {
		Game game = gameRepository.getReferenceById(data.game_id());
		Fighter fighter = new Fighter(data, game);
		fighterRepository.save(fighter);
		
		URI uri = uriBuilder.path("/games/{id}").buildAndExpand(fighter.getId()).toUri();
		return ResponseEntity.created(uri).body(new FighterRegistrationData(fighter));
	}
	
	@PutMapping("change")
	@Transactional
	public ResponseEntity<FighterRegistrationData> update(@RequestBody @Valid FighterUpdateData data) {
		Fighter fighter = fighterRepository.getReferenceById(data.id());
		Game game = gameRepository.getReferenceById(data.game_id());
		fighter.updateData(data, game);
		return ResponseEntity.ok(new FighterRegistrationData(fighter));
	}
	
	@DeleteMapping("delete/{id}")
	@Transactional
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		fighterRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
