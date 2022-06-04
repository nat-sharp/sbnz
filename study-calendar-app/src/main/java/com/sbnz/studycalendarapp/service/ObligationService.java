package com.sbnz.studycalendarapp.service;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.model.StudyCalendar;
import com.sbnz.studycalendarapp.model.StudySession;
import com.sbnz.studycalendarapp.repository.ObligationRepository;
import com.sbnz.studycalendarapp.repository.StudentRepository;
import com.sbnz.studycalendarapp.repository.StudyCalendarRepository;
import com.sbnz.studycalendarapp.repository.StudySessionRepository;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.studycalendarapp.model.Subject;

@Service
public class ObligationService {

	@Autowired
	private ObligationRepository repository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudySessionRepository studySessionRepository;
	
	@Autowired
	private StudyCalendarRepository studyCalendarRepository;
	
	private static Logger log = LoggerFactory.getLogger(ObligationService.class);

	private final KieContainer kieContainer;
	
	
	
	@Autowired
	public ObligationService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
	
	public List<Obligation> registerObligations(List<Obligation> obligations) {
		
		List<Obligation> saved = new ArrayList<>();
		for(Obligation o : obligations) {
			saved.add(repository.save(o));
		}
		
		Student student = saved.get(0).getSubject().getStudent();
		
		StudyCalendar calendar = new StudyCalendar();
		calendar.setObligations(saved);
		calendar.setStudent(student);
		
		//////////////////////////////////////////////////////////////////////////
		
		KieSession kieSession = kieContainer.newKieSession();
		
		kieSession.insert(calendar);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		////////////////////////////////////////////////////////////////////////		
		
		for(StudySession session : calendar.getSessions()) {
			studySessionRepository.save(session);
		}
		
		studyCalendarRepository.save(calendar);
		
		return obligations;
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
