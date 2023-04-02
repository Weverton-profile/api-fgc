package br.com.apifgc.dto.game;

import java.util.ArrayList;
import java.util.List;

import br.com.apifgc.dto.fighter.FighterData;
import br.com.apifgc.model.Fighter;
import br.com.apifgc.model.Game;

public record GameRegistrationAllData(
		Long id,
		String name,
		String urlImage,
		List<FighterData> fighters
		) {
	
		public GameRegistrationAllData(Game game) {
			this(game.getId_game(), game.getName(), game.getUrlImage(), showFighters(game.getFighters()));
		}
			
		private static List<FighterData> showFighters(List<Fighter> fighters) {
			List<FighterData> fightersData = new ArrayList<>();
			for (Fighter fighter : fighters) {
				fightersData.add(new FighterData(fighter));
			}
		return fightersData;
	}
}
