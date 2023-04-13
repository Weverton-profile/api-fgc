package br.com.apifgc.dto.combo;

import jakarta.validation.constraints.NotNull;

public record ComboDelete(
		@NotNull
		Long id_user,
		@NotNull
		Long id_combo
		) {

}
