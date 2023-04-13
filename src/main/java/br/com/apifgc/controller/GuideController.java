package br.com.apifgc.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.apifgc.dto.combo.ComboDelete;
import br.com.apifgc.dto.combo.ComboRegistrationData;
import br.com.apifgc.dto.combo.ComboUpdateData;
import br.com.apifgc.dto.fighter.FighterAllData;
import br.com.apifgc.dto.guide.GuideAllData;
import br.com.apifgc.dto.guide.GuideDelete;
import br.com.apifgc.dto.guide.GuideRegistrationData;
import br.com.apifgc.dto.guide.GuideUpdateData;
import br.com.apifgc.model.Combo;
import br.com.apifgc.model.Fighter;
import br.com.apifgc.model.Guide;
import br.com.apifgc.model.User;
import br.com.apifgc.repository.ComboRepository;
import br.com.apifgc.repository.FighterRepository;
import br.com.apifgc.repository.GuideRepository;
import br.com.apifgc.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("guide")
public class GuideController {
	
	@Autowired
	private GuideRepository guideRepository;
	@Autowired
	private FighterRepository fighterRepository;
	@Autowired
	private ComboRepository comboRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("show/{id}")
	public ResponseEntity<GuideAllData> detail(@PathVariable Long id) {
		Guide guide = guideRepository.getReferenceById(id);
		return ResponseEntity.ok(new GuideAllData(guide));
	}
	
	@PostMapping("new")
	@Transactional
	public ResponseEntity<FighterAllData> register(@RequestBody @Valid GuideRegistrationData data, UriComponentsBuilder uriBuilder) {
		Fighter fighter = fighterRepository.getReferenceById(data.fighter_id());
		User user = userRepository.getReferenceById(data.user_id());
		Guide guide = new Guide(data, fighter, user);
		guideRepository.save(guide);
		
		for (ComboRegistrationData combos : data.combos()) {
			Combo combo = new Combo(combos, guide);
			comboRepository.save(combo);
		}
		
		URI uri = uriBuilder.path("/fighter/show/{id}").buildAndExpand(guide.getId()).toUri();
		return ResponseEntity.created(uri).body(new FighterAllData(fighter));
	}
	
	@PreAuthorize("principal.id == #data.id_user()")
	@PutMapping("change")
	@Transactional
	public ResponseEntity<GuideAllData> update(@RequestBody @Valid GuideUpdateData data) {
		Guide guide = guideRepository.getReferenceById(data.id());
		for (ComboUpdateData combos : data.combos()) {
			Combo combo = comboRepository.getReferenceById(combos.id());
			combo.updateData(combos);
		}
		guide.updateData(data);
		return ResponseEntity.ok(new GuideAllData(guide));
	}
	
	@PreAuthorize("principal.id == #data.id_user()")
	@DeleteMapping("delete/{id}")
	@Transactional
	public ResponseEntity<Object> delete(@PathVariable GuideDelete data) {
		guideRepository.deleteById(data.id_guide());
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("principal.id == #data.id_user()")
	@DeleteMapping("combo/delete/{id}")
	@Transactional
	public ResponseEntity<Object> deleteCombo(@RequestBody @Valid ComboDelete data) {
		guideRepository.deleteById(data.id_combo());
		return ResponseEntity.noContent().build();
	}
}
