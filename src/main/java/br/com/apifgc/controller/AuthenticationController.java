package br.com.apifgc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apifgc.dto.user.AuthenticationData;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping
	public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationData data) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		Authentication authentication = authenticationManager.authenticate(token);
		return ResponseEntity.ok().build();
	}
	
}
