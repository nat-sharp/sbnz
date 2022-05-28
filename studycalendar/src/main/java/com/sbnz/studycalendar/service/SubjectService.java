package com.sbnz.studycalendar.service;

import com.sbnz.studycalendar.model.Subject;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service	
@Slf4j	
public class SubjectService {

	private final KieContainer kieContainer;
	
	@Autowired
	public SubjectService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
	
	public Subject getGradedSubject(Subject subject) {
		KieSession kieSession = kieContainer.newKieSession();
		
		kieSession.insert(subject);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return subject;
	}
	
}
