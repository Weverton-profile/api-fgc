package br.com.apifgc.dto.game;

import br.com.apifgc.model.Game;
import jakarta.validation.constraints.NotBlank;

public record GameRegistrationData(
		@NotBlank
		String name,
		@NotBlank
		String urlImage
		) {

	public GameRegistrationData(Game game) {
		this(game.getName(), game.getUrlImage());
	}
}
