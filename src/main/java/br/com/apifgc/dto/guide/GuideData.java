package br.com.apifgc.dto.guide;

import br.com.apifgc.model.Guide;

public record GuideData(
		Long id,
		String name
		) {

	public GuideData(Guide guide) {
		this(guide.getId(), guide.getName());
	}

}
