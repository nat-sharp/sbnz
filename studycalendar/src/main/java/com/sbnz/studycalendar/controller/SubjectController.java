package com.sbnz.studycalendar.controller;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.studycalendar.model.Subject;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
	
	@Autowired
	private KieSession kieSession;
	
	@GetMapping()
	public ResponseEntity<Subject> getSubject(@RequestBody Subject subject) {
		kieSession.insert(subject);
		System.out.println("DRUGO " + subject.getName());
		kieSession.fireAllRules();
		System.out.println("TRECE " + subject.getName());
		return new ResponseEntity<>(subject, HttpStatus.OK);
	}
}
