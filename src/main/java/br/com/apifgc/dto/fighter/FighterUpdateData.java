package br.com.apifgc.dto.fighter;

import jakarta.validation.constraints.NotNull;

public record FighterUpdateData(
		@NotNull
		Long id,
		Long game_id,
		String name,
		String urlImage
		) {
}
