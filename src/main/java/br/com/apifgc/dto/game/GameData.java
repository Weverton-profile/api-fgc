package br.com.apifgc.dto.game;

import br.com.apifgc.model.Game;

public record GameData(
		Long id,
		String name,
		String urlImage
		) {
	
	public GameData(Game game) {
		this(game.getId_game(), game.getName(), game.getUrlImage());
	}
}
