package br.com.apifgc.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.apifgc.model.Admin;
import br.com.apifgc.model.Owner;
import br.com.apifgc.model.User;

@Service
public class TokenService {
	
	@Value("{apifgc.security.token.secret}")
	private String secret;
	
	public String createToken(User user) {
		if (user.getRole().equals("ROLE_ADMIN")) {
			user = new Admin(user.getId(), user.getName(), user.getEmail(), user.getRole());
		} else if (user.getRole().equals("ROLE_OWNER")) {
			user = new Owner(user.getId(), user.getName(), user.getEmail(), user.getRole());
		}
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("API FGC")
					.withSubject(user.getEmail())
					.withClaim("id", user.getId())
					.withClaim("name", user.getName())
					.withClaim("role", user.getAuthorities().get(0).getAuthority())
					.withExpiresAt(expirationDate())
					.sign(algorithm);
		} catch (JWTCreationException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getSubject(String tokenJWT) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("API FGC")
					.build()
					.verify(tokenJWT).getSubject();
		} catch (JWTCreationException e) {
			throw new RuntimeException(e);
		}
	}

	private Instant expirationDate() {
		return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
	}
	
}
