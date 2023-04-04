package br.com.apifgc.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.apifgc.dto.combo.ComboData;
import br.com.apifgc.dto.guide.GuideAllData;
import br.com.apifgc.dto.guide.GuideRegistrationData;
import br.com.apifgc.model.Combo;
import br.com.apifgc.model.Fighter;
import br.com.apifgc.model.Guide;
import br.com.apifgc.repository.ComboRepository;
import br.com.apifgc.repository.FighterRepository;
import br.com.apifgc.repository.GuideRepository;
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
	
	@GetMapping("show/{id}")
	public ResponseEntity<GuideAllData> detail(@PathVariable Long id) {
		Guide guide = guideRepository.getReferenceById(id);
		return ResponseEntity.ok(new GuideAllData(guide));
	}
	
	@PostMapping("new")
	@Transactional
	public ResponseEntity<GuideAllData> register(@RequestBody @Valid GuideRegistrationData data, UriComponentsBuilder uriBuilder) {
		Fighter fighter = fighterRepository.getReferenceById(data.fighter_id());
		Guide guide = new Guide(data, fighter);
		guideRepository.save(guide);
		
		for (ComboData combos : data.combos()) {
			Combo combo = new Combo(combos, guide);
			comboRepository.save(combo);
		}
		
		URI uri = uriBuilder.path("/show/{id}").buildAndExpand(guide.getId()).toUri();
		return ResponseEntity.created(uri).body(new GuideAllData(guide));
	}
}
