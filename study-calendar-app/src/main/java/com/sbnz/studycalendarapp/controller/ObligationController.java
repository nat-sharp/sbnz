package com.sbnz.studycalendarapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.studycalendarapp.dto.CreateObligationDto;
import com.sbnz.studycalendarapp.dto.FinishObligationDto;
import com.sbnz.studycalendarapp.dto.ObligationDto;
import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.Subject;
import com.sbnz.studycalendarapp.service.ObligationService;
import com.sbnz.studycalendarapp.service.SubjectService;
import com.sbnz.studycalendarapp.util.Mapper;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/obligation")
public class ObligationController {

	@Autowired
	private ObligationService service;
	
	@Autowired
	private SubjectService subjectService;

	@Autowired
	private Mapper mapper;
	
	@GetMapping("/subject/{name}")
	public ResponseEntity<?> getObligationsForSubject(@PathVariable String name) {
		Subject subject = subjectService.findOneByName(name);
		if (subject == null) {
			return new ResponseEntity<String>("There is no subject with name '" + name + "'!", HttpStatus.BAD_REQUEST);
		}
		
		List<Obligation> obligations = service.findAllBySubject(subject);
		List<ObligationDto> dtos = new ArrayList<>();
		for (Obligation obligation : obligations) {
			dtos.add(mapper.toObligationDto(obligation));
		}
		
		return new ResponseEntity<List<ObligationDto>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping("/add") // TODO: izmeniti
	public ResponseEntity<String> addObligation(@RequestBody Obligation obligation){
		Subject subject = subjectService.findOneById(1);
		obligation.setSubject(subject);
		service.save(obligation);
		
		return new ResponseEntity<String>("Successfully added obligation!", HttpStatus.OK);
	}
	
	@PostMapping("/finish")
	public ResponseEntity<String> finishObligation(@RequestBody FinishObligationDto dto) {
		Obligation obligation = service.findOneById(dto.getId());
		obligation.setSkipped(dto.isSkipped());
		obligation.setEarnedPoints(dto.getEarnedPoints());
		
		Obligation finishedObligation = service.finishObligation(obligation);

		if (finishedObligation != null) {
			return new ResponseEntity<>("Successfully finished obligation!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping()
	public ResponseEntity<List<Obligation>> createObligations(@RequestBody List<CreateObligationDto> dtos) {
		// konvertovati dto u Obligation
		List<Obligation> obligations = new ArrayList<>();
		for(CreateObligationDto dto : dtos) {
			obligations.add(mapper.toObligation(dto));
		}
		
		obligations = service.registerObligations(obligations);
		
		return new ResponseEntity<>(obligations, HttpStatus.OK);
	}
}
