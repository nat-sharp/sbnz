package com.sbnz.studycalendarapp.service;

import com.sbnz.studycalendarapp.repository.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	private static Logger log = LoggerFactory.getLogger(StudentService.class);
}
