package com.sbnz.studycalendarapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.studycalendarapp.dto.LoginDto;
import com.sbnz.studycalendarapp.dto.StudentDto;
import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.service.StudentService;
import com.sbnz.studycalendarapp.util.Mapper;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@Autowired
	private Mapper mapper;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto dto) {
		Student student = service.findByUsername(dto.getUsername());
		
		if (student == null) {
			return new ResponseEntity<>("There is no student with username '"+ dto.getUsername() + "'!", HttpStatus.BAD_REQUEST);
		}
		
		if (!student.getPassword().equals(dto.getPassword())) {
			return new ResponseEntity<>("You entered the wrong password!", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Successfully logged in as student!", HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody StudentDto dto) {
		Student student = mapper.toStudent(dto);
		service.save(student);
		
		return new ResponseEntity<>("Successfully registered!", HttpStatus.OK);
	}
}
