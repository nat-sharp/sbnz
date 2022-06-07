package com.sbnz.studycalendarapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.StudyCalendar;
import com.sbnz.studycalendarapp.model.StudySession;
import com.sbnz.studycalendarapp.repository.ObligationRepository;
import com.sbnz.studycalendarapp.repository.StudySessionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudySessionService {
	
	@Autowired
	private StudySessionRepository repository;
	
	@Autowired
	private ObligationRepository obligationRepository;
	
	private static Logger log = LoggerFactory.getLogger(StudySessionService.class);

	
	public StudySession newStudySession(StudyCalendar cal, Integer id, LocalDate date) {
		Obligation o = obligationRepository.findOneById(id);
		LocalDateTime dateAndTime = date.atStartOfDay();
		StudySession session =  new StudySession();
		session.setDateAndTime(dateAndTime);
		session.setObligation(o);
		session.setStudyCalendar(cal);
		return session;
	}
}
