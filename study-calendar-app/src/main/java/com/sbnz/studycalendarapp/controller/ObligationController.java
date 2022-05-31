package com.sbnz.studycalendarapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.studycalendarapp.dto.CreateObligationDto;
import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.service.ObligationService;
import com.sbnz.studycalendarapp.util.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/obligation")
public class ObligationController {

	@Autowired
	private ObligationService service;
	
	@Autowired
	private Mapper mapper;
	
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
