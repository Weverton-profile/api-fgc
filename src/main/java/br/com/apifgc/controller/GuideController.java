package br.com.apifgc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apifgc.repository.GuideRepository;

@RestController
@RequestMapping("guide")
public class GuideController {
	
	@SuppressWarnings("unused")
	@Autowired
	private GuideRepository guideRepository;
	
}
