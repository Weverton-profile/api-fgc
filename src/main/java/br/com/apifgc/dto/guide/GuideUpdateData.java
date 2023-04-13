package br.com.apifgc.dto.guide;

import java.util.List;

import br.com.apifgc.dto.combo.ComboUpdateData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GuideUpdateData(
		@NotNull
		Long id_user,
		@NotNull
		Long id,
		@NotBlank
		String name,
		@NotBlank
		String description,
		@NotBlank
		List<String> strengths,
		@NotBlank
		List<String> weaknesses,
		@NotBlank
		List<ComboUpdateData> combos
		) {
}
