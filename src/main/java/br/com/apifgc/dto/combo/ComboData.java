package br.com.apifgc.dto.combo;

import br.com.apifgc.model.Combo;
import jakarta.validation.constraints.NotNull;

public record ComboData(
		Long id,
		@NotNull
		String combo,
		String url
		) {

	public ComboData(Combo combo) {
		this(combo.getId(), combo.getCombo(), combo.getUrlVideo());
	}
	
}
