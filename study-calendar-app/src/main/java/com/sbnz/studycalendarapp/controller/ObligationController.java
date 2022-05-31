package com.sbnz.studycalendarapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.studycalendar.dto.ObligationDTO;
import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.Subject;
import com.sbnz.studycalendarapp.service.ObligationService;
import com.sbnz.studycalendarapp.service.SubjectService;

@RestController
@RequestMapping("/api/obligation")
public class ObligationController {
	
	@Autowired
	private ObligationService service;	
	
	@Autowired
	private SubjectService subjectService;
	
	@PostMapping("/add") // TODO: izmeniti
	public ResponseEntity<String> addObligation(@RequestBody Obligation obligation){
		Subject subject = subjectService.findOne(1);
		obligation.setSubject(subject);
		service.save(obligation);
		
		return new ResponseEntity<String>("Successfully added obligation!", HttpStatus.OK);
	}
	
	@PostMapping("/finish")
	public ResponseEntity<String> finishObligation(@RequestBody ObligationDTO dto) {
		Obligation obligation = service.findOne(dto.getId());
		obligation.setSkipped(dto.isSkipped());
		obligation.setEarnedPoints(dto.getEarnedPoints());
		
		Obligation finishedObligation = service.finishObligation(obligation);

		if (finishedObligation != null) {
			return new ResponseEntity<>("Successfully finished obligation!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
}
