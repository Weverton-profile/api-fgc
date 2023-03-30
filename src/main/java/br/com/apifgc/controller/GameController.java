package br.com.apifgc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apifgc.model.Game;

@RestController
@RequestMapping("games")
public class GameController {
	
	@GetMapping("show")
	public ResponseEntity<Game> detail() {
		return null;
	}
	
	@PostMapping("new")
	public ResponseEntity<Game> register() {
		return null;
	}
	
	@PutMapping("change")
	public ResponseEntity<Game> update() {
		return null;
	}
}
