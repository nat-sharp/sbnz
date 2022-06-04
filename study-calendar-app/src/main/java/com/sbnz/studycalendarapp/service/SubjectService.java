package com.sbnz.studycalendarapp.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.studycalendarapp.model.Subject;
import com.sbnz.studycalendarapp.repository.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository repository;
	
	private static Logger log = LoggerFactory.getLogger(SubjectService.class);
	
	private final KieContainer kieContainer;
	
	@Autowired
	public SubjectService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
	
	public Subject findOne(Integer id) {
		return repository.findOneById(id);
	}
	
	public Subject save(Subject subject) {
		return repository.save(subject);
	}
	
	public Subject getGradedSubject(Subject subject) {
		KieSession kieSession = kieContainer.newKieSession();
		
		kieSession.insert(subject);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return subject;
	}
	
}
