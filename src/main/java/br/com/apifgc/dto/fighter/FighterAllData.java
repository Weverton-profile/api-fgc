package br.com.apifgc.dto.fighter;

import java.util.ArrayList;
import java.util.List;

import br.com.apifgc.dto.guide.GuideData;
import br.com.apifgc.model.Fighter;
import br.com.apifgc.model.Guide;

public record FighterAllData(
		Long id,
		String name,
		String urlImage,
		List<GuideData> guides
		) {

	public FighterAllData(Fighter fighter) {
		this(fighter.getId(), fighter.getName(), fighter.getUrlImage(), showGuides(fighter.getGuides()));
	}

	private static List<GuideData> showGuides(List<Guide> guides) {
		List<GuideData> guideData = new ArrayList<>();
		for (Guide guide : guides) {
			guideData.add(new GuideData(guide));
		}
		return guideData;
	}
	
}
