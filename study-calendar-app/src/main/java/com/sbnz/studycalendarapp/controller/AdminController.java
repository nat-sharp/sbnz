package com.sbnz.studycalendarapp.controller;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.studycalendarapp.dto.LoginDto;
import com.sbnz.studycalendarapp.dto.ReportDto;
import com.sbnz.studycalendarapp.dto.ReportUnitDto;
import com.sbnz.studycalendarapp.model.Admin;
import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.model.StudyCalendar;
import com.sbnz.studycalendarapp.service.AdminService;
import com.sbnz.studycalendarapp.service.StudentService;
import com.sbnz.studycalendarapp.service.StudyCalendarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService service;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudyCalendarService calService;
	
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
	
	@GetMapping("/report")
	public ResponseEntity<ReportDto> getReport() {
		
		List<Student> students = studentService.getAll();
		
		List<ReportUnitDto> dtos = new ArrayList<>();
		for(Student s: students) {
			ReportUnitDto dto = new ReportUnitDto();
			dto.setStudentId(s.getId());
			dto.setStudentName(s.getFirstName() + " " + s.getLastName());
			dto.setCategory(s.getActivity().name());
			dto.setPoints(s.getActivityPoints().floatValue());
			
			StudyCalendar sc = calService.getByStudentId(s.getId());
			
			String status = studentService.calculateStatus(sc);
			
			dto.setStatus(status);
			
			dtos.add(dto);
		}
		
		return new ResponseEntity<>(new ReportDto(dtos), HttpStatus.OK);
		
	}
}
