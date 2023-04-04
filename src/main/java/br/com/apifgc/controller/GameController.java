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

import br.com.apifgc.dto.game.GameData;
import br.com.apifgc.dto.game.GameAllData;
import br.com.apifgc.dto.game.GameRegistrationData;
import br.com.apifgc.dto.game.GameUpdateData;
import br.com.apifgc.model.Game;
import br.com.apifgc.repository.GameRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("games")
public class GameController {
	
	@Autowired
	private GameRepository gameRepository;
	
	@GetMapping("show/{id}")
	public ResponseEntity<GameAllData> detail(@PathVariable Long id) {
		Game game = gameRepository.getReferenceById(id);
		return ResponseEntity.ok(new GameAllData(game));
	}
	
	@GetMapping("list")
	public ResponseEntity<Page<GameData>> list(@PageableDefault(size = 10) Pageable pagination) {
		Page<GameData> page = gameRepository.findAll(pagination).map(GameData::new);
		return ResponseEntity.ok(page);
	}
	
	@PostMapping("new")
	@Transactional
	public ResponseEntity<GameRegistrationData> register(@RequestBody @Valid GameRegistrationData data, UriComponentsBuilder uriBuilder) {
		Game game = new Game(data);
		gameRepository.save(game);
		
		URI uri = uriBuilder.path("/games/{id}").buildAndExpand(game.getId_game()).toUri();
		return ResponseEntity.created(uri).body(new GameRegistrationData(game));
	}
	
	@PutMapping("change")
	@Transactional
	public ResponseEntity<GameRegistrationData> update(@RequestBody @Valid GameUpdateData data) {
		Game game = gameRepository.getReferenceById(data.id());
		game.updateData(data);
		return ResponseEntity.ok(new GameRegistrationData(game));
	}
	
	@DeleteMapping("delete/{id}")
	@Transactional
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		gameRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
