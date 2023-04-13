package br.com.apifgc.dto.guide;

import jakarta.validation.constraints.NotNull;

public record GuideDelete(
		@NotNull
		Long id_user,
		@NotNull
		Long id_guide		
		) {

}
