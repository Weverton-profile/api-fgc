package br.com.apifgc.dto.fighter;

import br.com.apifgc.model.Fighter;

public record FighterData(
		Long id,
		String name,
		String urlImage
		) {

	public FighterData(Fighter fighter) {
		this(fighter.getId(), fighter.getName(), fighter.getUrlImage());
	}
	
}
