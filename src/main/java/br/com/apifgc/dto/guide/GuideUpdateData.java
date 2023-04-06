package br.com.apifgc.dto.guide;

import java.util.List;

import br.com.apifgc.dto.combo.ComboUpdateData;
import jakarta.validation.constraints.NotNull;

public record GuideUpdateData(
		@NotNull
		Long id,
		String name,
		String description,
		List<String> strengths,
		List<String> weaknesses,
		List<ComboUpdateData> combos
		) {
}
