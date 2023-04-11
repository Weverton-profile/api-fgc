package br.com.apifgc.dto.guide;

import java.util.ArrayList;
import java.util.List;

import br.com.apifgc.dto.combo.ComboData;
import br.com.apifgc.model.Combo;
import br.com.apifgc.model.Guide;

public record GuideAllData(

		Long id,
		String userName,
		String fighter,
		String name,
		String description,
		List<String> strengths,
		List<String> weaknesses,
		List<ComboData> combos
		) {

	public GuideAllData(Guide guide) {
		this(guide.getId(), guide.getUser().getName(), guide.getFighter().getName(), guide.getName(), 
				guide.getDescription(), guide.getStrengths(), guide.getWeaknesses(), combosForComboData(guide.getCombos()));
	}

	private static List<ComboData> combosForComboData(List<Combo> combos) {
		List<ComboData> comboData = new ArrayList<>();
		for (Combo combo : combos) {
			comboData.add(new ComboData(combo));
		}
		return comboData;
	}

}
