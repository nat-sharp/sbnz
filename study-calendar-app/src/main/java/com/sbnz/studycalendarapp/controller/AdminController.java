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
import com.sbnz.studycalendarapp.model.Admin;
import com.sbnz.studycalendarapp.service.AdminService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto dto) {
		Admin admin = service.findByUsername(dto.getUsername());
		
		if (admin == null) {
			return new ResponseEntity<>("There is no admin with username '"+ dto.getUsername() + "'!", HttpStatus.BAD_REQUEST);
		}
		
		if (!admin.getPassword().equals(dto.getPassword())) {
			return new ResponseEntity<>("You entered the wrong password!", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>("Successfully logged in as an admin!", HttpStatus.OK);
	}
}
