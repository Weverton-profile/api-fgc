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
import br.com.apifgc.dto.user.TokenDadosJWT;
import br.com.apifgc.infra.security.TokenService;
import br.com.apifgc.model.User;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationData data) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		String tokenJWT = tokenService.createToken((User) authentication.getPrincipal());
		return ResponseEntity.ok(new TokenDadosJWT(tokenJWT));
	}
	
}
