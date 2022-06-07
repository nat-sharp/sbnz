package com.sbnz.studycalendarapp.service;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.model.StudyCalendar;
import com.sbnz.studycalendarapp.model.StudySession;
import com.sbnz.studycalendarapp.model.Subject;
import com.sbnz.studycalendarapp.repository.ObligationRepository;
import com.sbnz.studycalendarapp.repository.StudyCalendarRepository;
import com.sbnz.studycalendarapp.repository.StudySessionRepository;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObligationService {
	@Autowired
	private ObligationRepository repository;
	
	@Autowired
	private StudySessionRepository studySessionRepository;
	
	@Autowired
	private StudyCalendarRepository studyCalendarRepository;
	
	@Autowired
	private StudyCalendarService studyCalendarService;
	
	@Autowired
	private StudySessionService studySessionService;
	
	private static Logger log = LoggerFactory.getLogger(ObligationService.class);

	private final KieContainer kieContainer;
	
	@Autowired
	public ObligationService(KieContainer kieContainer) {
		log.info("Initialising session for obligations.");
		this.kieContainer = kieContainer;
	}
	
	public List<Obligation> registerObligations(List<Obligation> obligations) {
		System.out.println("HEJ USLI SMO U REGISTER OBLIGATIONS");
		List<Obligation> saved = new ArrayList<>();
		for(Obligation o : obligations) {
			saved.add(repository.save(o));
		}
		
		Student student = saved.get(0).getSubject().getStudent();
		
		StudyCalendar calendar = new StudyCalendar();
		calendar.setObligations(saved);
		calendar.setStudent(student);
		studyCalendarRepository.save(calendar);
		System.out.println("SAD KREIRAMO KIE SESSION");
		//////////////////////////////////////////////////////////////////////////
		
		KieSession kieSession = kieContainer.newKieSession("studySessions");
		kieSession.setGlobal("calendarService", this.studyCalendarService);
		kieSession.setGlobal("studySessionService", this.studySessionService);
		
		System.out.println("SAD INSERTUJEMO KALENDAR");
		kieSession.insert(calendar);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		////////////////////////////////////////////////////////////////////////		
		
		for(StudySession session : calendar.getSessions()) {
			System.out.print("____________SESIJA_____________");
			System.out.print(session);
			studySessionRepository.save(session);
		}
		
		studyCalendarRepository.save(calendar);
		
		return obligations;
	}

	public Obligation findOneById(Integer id) {
		return repository.findOneById(id);
	}
	
	public Obligation save(Obligation obligation) {
		return repository.save(obligation);
	}
	
	public Obligation finishObligation(Obligation obligation) {
		KieSession kieSession = kieContainer.newKieSession("obligations");
		
		kieSession.insert(obligation);
		Subject subject = obligation.getSubject();
		kieSession.insert(subject);
		kieSession.insert(subject.getStudent());
		kieSession.setGlobal("$finishedObligations", new ArrayList<Obligation>());
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return save(obligation);
	}
	
	public List<Obligation> findAllBySubject(Subject subject) {
		return repository.findAllBySubject(subject);
	}
}
