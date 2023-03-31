package br.com.apifgc.model.game;

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
