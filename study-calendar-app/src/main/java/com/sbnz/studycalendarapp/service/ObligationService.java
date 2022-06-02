package com.sbnz.studycalendarapp.service;

import java.util.ArrayList;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.Subject;
import com.sbnz.studycalendarapp.repository.ObligationRepository;

@Service
public class ObligationService {

	@Autowired
	private ObligationRepository repository;

	private static Logger log = LoggerFactory.getLogger(ObligationService.class);
	
	private final KieContainer kieContainer;
	
	@Autowired
	public ObligationService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
	
	public Obligation findOne(Integer id) {
		return repository.findOneById(id);
	}
	
	public Obligation save(Obligation obligation) {
		return repository.save(obligation);
	}
	
	public Obligation finishObligation(Obligation obligation) {
		KieSession kieSession = kieContainer.newKieSession();
		
		kieSession.insert(obligation);
		Subject subject = obligation.getSubject();
		kieSession.insert(subject);
		kieSession.insert(subject.getStudent());
		kieSession.setGlobal("$finishedObligations", new ArrayList<Obligation>());
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return save(obligation);
	}
}
