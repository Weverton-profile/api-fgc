package br.com.apifgc.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Admin extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Admin(Long id, String name, String email, String role) {
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setRole(role);
	}

	@Override
	public List<SimpleGrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
		simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return simpleGrantedAuthorities;
	}
}
