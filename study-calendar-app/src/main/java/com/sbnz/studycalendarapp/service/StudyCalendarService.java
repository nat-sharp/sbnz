package com.sbnz.studycalendarapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.model.StudyCalendar;
import com.sbnz.studycalendarapp.model.StudySession;
import com.sbnz.studycalendarapp.repository.ObligationRepository;
import com.sbnz.studycalendarapp.repository.StudyCalendarRepository;
import com.sbnz.studycalendarapp.repository.StudySessionRepository;
import com.sbnz.studycalendarapp.util.SortByAvg;
import com.sbnz.studycalendarapp.util.SortByPriority;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyCalendarService {
	
	@Autowired
	private StudyCalendarRepository studyCalendarRepository;
	
	@Autowired
	private ObligationRepository obligationRepository;
	
	@Autowired
	private StudySessionRepository sessionRepository;
	
	private static Logger log = LoggerFactory.getLogger(StudyCalendarService.class);
	
	private final KieContainer kieContainer;
	
	@Autowired
	public StudyCalendarService(KieContainer kieContainer) {
		log.info("Initialising session for study calendar.");
		this.kieContainer = kieContainer;
	}
	
	public StudyCalendar getByStudentId(Integer id) {
		return this.studyCalendarRepository.findByStudentId(id);
	}
	
	public StudySession getById(Integer id) {
		return this.sessionRepository.getById(id);
	}
	
	public StudySession save(StudySession s) {
		return this.sessionRepository.save(s);
	}
	
	public List<StudySession> getPrioritizedSessions(List<StudySession> sessions){
		Collections.sort(sessions,  Collections.reverseOrder(new SortByPriority()));
		return sessions.stream().map(s -> new StudySession(s, sessions.indexOf(s) + 1)).collect(Collectors.toList());
	}

	
	public List<StudySession> makeSessions(List<Obligation> obligations) {
		
		System.out.println("_____________________________EVO USLI SMO U MAKE SESSIONS SC SERVICE");
		System.out.println(obligations);
		Student student = obligations.get(0).getSubject().getStudent();
		
		
		StudyCalendar calendar = studyCalendarRepository.findByStudentId(student.getId());
		if(calendar == null) {
			calendar = new StudyCalendar();
		}
		calendar.setObligations(obligations);
		calendar.setStudent(student);
		
		//////////////////////////////////////////////////////////////////////////
		
		KieSession kieSession = kieContainer.newKieSession("studySessions");
		
		kieSession.insert(calendar);
		kieSession.setGlobal("service", this);
		kieSession.setGlobal("$tempList", new ArrayList<StudySession>());
		kieSession.fireAllRules();
		kieSession.dispose();
		
		////////////////////////////////////////////////////////////////////////		
		
		calendar = studyCalendarRepository.save(calendar);
		
		for(StudySession session : calendar.getSessions()) {
			session.setStudyCalendar(calendar);
			sessionRepository.save(session);
			
		}
		
		return calendar.getSessions();
	}
	
	private List<StudySession> getListFromMap(Map<LocalDate, ArrayList<StudySession>> sess) {
		List<StudySession> lista = new ArrayList<>();
		
		for(LocalDate date : sess.keySet()) {
			lista.addAll(sess.get(date));
		}
		return lista;
	}

	private Map<LocalDate, ArrayList<StudySession>> getMapFromList(List<StudySession> lista) {
		Map<LocalDate, ArrayList<StudySession>> mapa = new HashMap<>();
		
		for(StudySession s : lista) {
			LocalDate date = s.getDate();
			boolean isKeyPresent = mapa.containsKey(date);
			if(!isKeyPresent) {
				mapa.put(date, new ArrayList<>(Arrays.asList(s))); 
			}else {
				ArrayList<StudySession> sesije = mapa.get(date);
				sesije.add(s);
				mapa.put(date, sesije);
			}
		}
		return mapa;
	}

	//CREATE SESSIONS
	public StudyCalendar createSessions(StudyCalendar cal) {
		//podelimo broj sati na broj dana
		List<Obligation> obs= new ArrayList<>(cal.getObligations());
		Map<LocalDate, ArrayList<StudySession>> sess= getMapFromList(cal.getSessions());
		
		Collections.sort(obs, new SortByAvg());
		
		
		//po tom rasporedu- krenemo
		//rasporedimo sesije ne manje od 2 sata redom svaki dan, i zaokruzene na vise od onog proseka
		for(Obligation o: obs) {
			float avgHrsPerDay = o.getStudyHours()/ (o.getStudyStartDate().datesUntil(o.getStudyEndDate().plusDays(1)).collect(Collectors.toList())).size();
			float hoursPerDay = avgHrsPerDay;
			if(avgHrsPerDay < 2) {
				hoursPerDay = 2;
			}
			
			float current = o.getStudyHours();
			for(LocalDate date : o.getStudyStartDate().datesUntil(o.getStudyEndDate().plusDays(1)).collect(Collectors.toList())) {
				if (current<= 0.0) {
					sess = clearEmptySessions(sess,o);
				}
				//provera da li je taj dan zauzet?
				ArrayList<StudySession> exList = sess.get(date); //lista sesija
				for(StudySession s : exList) {
					if(s.getObligation() == o) {
						s.setDurationInHours(hoursPerDay);
						current = current - hoursPerDay;
						break;
					}
				}
				sess.put(date, exList);
			}
			
			
		}
		List<StudySession> sessions = removeEmptySessions(getListFromMap(sess));
		cal.setSessions(sessions);
		return cal;
	}
	
	private List<StudySession> removeEmptySessions(List<StudySession> lista) {
		List<StudySession> result = new ArrayList<>();
		for(StudySession s: lista) {
			if(s.getDurationInHours() != 0.0) {
				result.add(s);
			}
		}
		return result;
	}


	private Map<LocalDate, ArrayList<StudySession>> clearEmptySessions(Map<LocalDate, ArrayList<StudySession>> sess2, Obligation o) {
		//sve sesije koje su za tu obligaciju i prazne su, izbrisi
		Map<LocalDate, ArrayList<StudySession>> sess=   new HashMap<>(sess2);
		
		for(LocalDate date : sess2.keySet()) {
			List<StudySession> lista = sess2.get(date);
			for(StudySession s: lista) {
				if(s.getObligation() == o && (s.getDurationInHours() == 0.0)) {
					//U NASOJ DUMMY LISTI BRISEMO
					System.out.println("_________________________EVO BRISEMO_______________________________________________________________");
					sess.get(date).remove(s); //TODO ili sa indexOf
				}
			}
		}
		return sess;
	}

	

}
