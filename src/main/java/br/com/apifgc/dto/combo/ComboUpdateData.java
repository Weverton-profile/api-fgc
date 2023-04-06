package br.com.apifgc.dto.combo;

import jakarta.validation.constraints.NotNull;

public record ComboUpdateData(
		@NotNull
		Long id,
		String combo,
		String urlVideo
		) {

}
