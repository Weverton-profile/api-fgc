package br.com.apifgc.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.apifgc.model.game.Game;
import br.com.apifgc.model.game.GameRegistrationData;
import br.com.apifgc.model.game.GameUpdateData;
import br.com.apifgc.repository.GameRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("games")
public class GameController {
	
	@Autowired
	private GameRepository gameRepository;
	
	@GetMapping("show/{id}")
	public ResponseEntity<GameRegistrationData> detail(@PathVariable Long id) {
		Game game = gameRepository.getReferenceById(id);
		return ResponseEntity.ok(new GameRegistrationData(game));
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
}
