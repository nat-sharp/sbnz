package com.sbnz.studycalendarapp.controller;



import com.sbnz.studycalendarapp.model.Subject;
import com.sbnz.studycalendarapp.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
	
	@Autowired
	private SubjectService service;	
	
	@GetMapping()
	public ResponseEntity<Subject> getSubject(@RequestParam String name) {
		Subject s = new Subject();
		s.setName(name);
		s = service.getGradedSubject(s);
				
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
}
