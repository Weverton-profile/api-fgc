package br.com.apifgc.dto.user;

import br.com.apifgc.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistration(
		@NotNull @NotBlank
		String name,
		@NotNull @NotBlank
		String email,
		@NotNull @NotBlank
		String password
		) {

	public UserRegistration(User user) {
		this(user.getName(), user.getEmail(), user.getPassword());
	}

}
