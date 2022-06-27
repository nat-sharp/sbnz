package com.sbnz.studycalendarapp.service;

import java.util.ArrayList;

import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.model.StudySession;
import com.sbnz.studycalendarapp.repository.StudentRepository;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	private static Logger log = LoggerFactory.getLogger(StudentService.class);
	
	private final KieContainer kieContainer;
	
	@Autowired
	public StudentService(KieContainer kieContainer) {
		log.info("Initialising session for status.");
		this.kieContainer = kieContainer;
	}
	
	public Student findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	public Student save(Student student) {
		return repository.save(student);
	}
	
	public String calculateStatus(Student s) {
		
		///////////////////////////////////////
		
		KieSession kieSession = kieContainer.newKieSession("backwards");
		//mogli bismo ovde insert new Fact, ali necemo
		
		
		kieSession.insert(null);
		kieSession.setGlobal("service", this);
		kieSession.setGlobal("$tempList", new ArrayList<StudySession>());
		kieSession.fireAllRules();
		kieSession.dispose();
		//////////////////////////////////////
		
		//obrada stringa?
		
		return "hello";
		
	}
}
