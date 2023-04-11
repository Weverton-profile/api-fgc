package br.com.apifgc.dto.user;

import java.util.ArrayList;
import java.util.List;

import br.com.apifgc.dto.guide.GuideData;
import br.com.apifgc.model.Guide;
import br.com.apifgc.model.User;

public record UserAllData(
		Long id,
		String name,
		List<GuideData> guides
		) {

	public UserAllData(User user) {
		this(user.getId(), user.getName(), guideForGuideData(user.getGuides()));
	}

	private static List<GuideData> guideForGuideData(List<Guide> guides) {
		List<GuideData> guideData = new ArrayList<>();
		for (Guide guide : guides) {
			guideData.add(new GuideData(guide));
		}
		return guideData;
	}

}
