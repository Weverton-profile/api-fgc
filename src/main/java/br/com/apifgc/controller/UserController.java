package br.com.apifgc.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.apifgc.dto.user.UserData;
import br.com.apifgc.dto.user.UserRegistration;
import br.com.apifgc.dto.user.UserUpdateData;
import br.com.apifgc.model.User;
import br.com.apifgc.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("show/{id}")
	public ResponseEntity<UserData> detail(@PathVariable Long id) {
		User user = userRepository.getReferenceById(id);
		return ResponseEntity.ok().body(new UserData(user));
	}
	
	@PostMapping("create")
	@Transactional
	public ResponseEntity<UserData> create(@RequestBody @Valid UserRegistration data, UriComponentsBuilder uriBuilder) {
		UserDetails userDetails = userRepository.findByEmail(data.email());
		if (userDetails != null) {
			return ResponseEntity.status(HttpStatusCode.valueOf(422)).build();
		}
		User user = new User(data);
		userRepository.save(user);
		URI uri = uriBuilder.path("/user/show/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserData(user));
	}
	
	@PutMapping("change")
	@Transactional
	public ResponseEntity<UserData> update(@RequestBody @Valid UserUpdateData data) {
		User user = userRepository.getReferenceById(data.id());
		UserDetails userTest = userRepository.findByEmail(data.email());
		if (userTest != null) {
			return ResponseEntity.status(HttpStatusCode.valueOf(422)).build();
		}
		user.updateData(data);
		return ResponseEntity.ok(new UserData(user));
	}
	
	@PutMapping("change-role/{id}")
	@Transactional
	public ResponseEntity<UserData> updateRole(@PathVariable Long id) {
		User user = userRepository.getReferenceById(id);
		user.updateRole();
		return ResponseEntity.ok(new UserData(user));
	}
	
	@DeleteMapping("delete/{id}")
	@Transactional
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		User user = userRepository.getReferenceById(id);
		
		if (user.getRole().equals("ROLE_ADMIN")
				|| user.getRole().equals("ROLE_OWNER")) {
			return ResponseEntity.status(HttpStatusCode.valueOf(422)).build();
		}
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("delete/admin/{id}")
	@Transactional
	public ResponseEntity<Object> deleteAdmin(@PathVariable Long id) {
		User user = userRepository.getReferenceById(id);
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
