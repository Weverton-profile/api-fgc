package br.com.apifgc.dto.game;

import jakarta.validation.constraints.NotNull;

public record GameUpdateData(
		@NotNull
		Long id,
		String name,
		String urlImage
		) {

}
