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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.studycalendarapp.dto.SubjectDto;
import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.model.Subject;
import com.sbnz.studycalendarapp.service.StudentService;
import com.sbnz.studycalendarapp.service.SubjectService;
import com.sbnz.studycalendarapp.util.Mapper;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/subject")
public class SubjectController {
	
	@Autowired
	private SubjectService service;	
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private Mapper mapper;
	
	@GetMapping("/student/{username}")
	public ResponseEntity<?> getSubjectsForStudent(@PathVariable String username) {
		Student student = studentService.findByUsername(username);
		if (student == null) {
			return new ResponseEntity<String>("There is no student with username '" + username + "'!", HttpStatus.BAD_REQUEST);
		}
		
		List<Subject> subjects = service.findAllByStudent(student);
		List<SubjectDto> dtos = new ArrayList<>();
		for (Subject subject : subjects) {
			dtos.add(mapper.toSubjectDto(subject));
		}
		
		return new ResponseEntity<List<SubjectDto>>(dtos, HttpStatus.OK);
	}
	
	@PostMapping("/add/{username}")
	public ResponseEntity<String> addSubject(@PathVariable String username, @RequestBody String name){
		Student student = studentService.findByUsername(username);
		if (student == null) {
			return new ResponseEntity<String>("There is no student with username '" + username + "'!", HttpStatus.BAD_REQUEST);
		}
		
		Subject subject = new Subject();
		subject.setName(name);
		subject.setEarnedPoints(0.0);
		subject.setPassed(false);
		subject.setGrade(0);
		subject.setFinished(false);
		subject.setStudent(student);
		service.save(subject);
		
		return new ResponseEntity<String>("Successfully added subject!", HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<Subject> getSubject(@RequestParam String name) {
		Subject s = new Subject();
		s.setName(name);
		s = service.getGradedSubject(s);
				
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
}
