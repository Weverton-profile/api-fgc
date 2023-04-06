package br.com.apifgc.dto.guide;

import java.util.List;

import br.com.apifgc.dto.combo.ComboRegistrationData;
import jakarta.validation.constraints.NotNull;

public record GuideRegistrationData(
		@NotNull
		Long fighter_id,
		@NotNull
		String name,
		@NotNull
		String description,
		List<String> strengths,
		List<String> weaknesses,
		@NotNull
		List<ComboRegistrationData> combos
		) {

}
