package br.com.apifgc.dto.guide;

import java.util.List;

import br.com.apifgc.dto.combo.ComboRegistrationData;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GuideRegistrationData(
		@NotNull @NotBlank
		Long fighter_id,
		@NotNull @NotBlank
		String name,
		@NotNull @NotBlank
		String description,
		List<String> strengths,
		List<String> weaknesses,
		@NotNull @NotBlank
		List<ComboRegistrationData> combos
		) {

}
