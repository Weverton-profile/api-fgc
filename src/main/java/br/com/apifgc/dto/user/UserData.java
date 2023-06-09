package br.com.apifgc.dto.user;

import br.com.apifgc.model.User;

public record UserData(
		Long id,
		String email,
		String name
		) {

	public UserData(User user) {
		this(user.getId(), user.getEmail(), user.getName());
	}

}
