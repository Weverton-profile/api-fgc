package br.com.apifgc.dto.combo;

import br.com.apifgc.model.Combo;

public record ComboData(
		Long id,
		String combo,
		String url
		) {

	public ComboData(Combo combo) {
		this(combo.getId(), combo.getCombo(), combo.getUrlVideo());
	}
	
}
