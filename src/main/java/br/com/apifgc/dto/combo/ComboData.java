package br.com.apifgc.dto.combo;

import br.com.apifgc.model.Combo;
import jakarta.validation.constraints.NotNull;

public record ComboData(
		@NotNull
		String combo,
		String url
		) {

	public ComboData(Combo combo) {
		this(combo.getCombo(), combo.getUrlVideo());
	}
	
}
