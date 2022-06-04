package com.sbnz.studycalendarapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	private static Logger log = LoggerFactory.getLogger(StudentService.class);
	
	public Student findByUsername(String username) {
		return repository.findByUsername(username);
	}
}
