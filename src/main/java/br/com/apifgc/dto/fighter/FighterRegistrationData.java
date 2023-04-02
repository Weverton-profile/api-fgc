package br.com.apifgc.dto.fighter;

import br.com.apifgc.model.Fighter;

public record FighterRegistrationData(
		Long game_id,
		String name,
		String urlImage
		) {

	public FighterRegistrationData(Fighter fighter) {
		this(fighter.getGame().getId_game(), fighter.getName(), fighter.getUrlImage());
	}

}
