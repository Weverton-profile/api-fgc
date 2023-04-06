package br.com.apifgc.dto.combo;

import br.com.apifgc.model.Combo;
import jakarta.validation.constraints.NotNull;

public record ComboRegistrationData(
		@NotNull
		String combo,
		String url
		) {
	
	public ComboRegistrationData(Combo combo) {
		this(combo.getCombo(), combo.getUrlVideo());
	}
}
