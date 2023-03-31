package br.com.apifgc.model.game;

import jakarta.validation.constraints.NotNull;

public record GameUpdateData(
		@NotNull
		Long id,
		String name,
		String urlImage
		) {

}
