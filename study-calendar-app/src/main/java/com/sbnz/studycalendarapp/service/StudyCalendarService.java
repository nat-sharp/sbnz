package com.sbnz.studycalendarapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.StudyCalendar;
import com.sbnz.studycalendarapp.model.StudySession;
import com.sbnz.studycalendarapp.repository.StudyCalendarRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyCalendarService {
	
	@Autowired
	private StudyCalendarRepository repository;
	
	private static Logger log = LoggerFactory.getLogger(StudyCalendarService.class);
	
	public List<LocalDate> getDatesInRange(LocalDate startDate, LocalDate endDate) {
		return startDate.datesUntil(endDate).collect(Collectors.toList());
	}
	
	//INJECT OBLIGATIONS
	
//	public StudyCalendar injectObligations(StudyCalendar cal) {
//		Map<LocalDate, List<StudySession>> sessions = new HashMap<>();
//		
//		for(Obligation obligation : cal.getObligations()) {
//			List<LocalDate> listOfDates = obligation.getStudyStartDate().datesUntil(obligation.getStudyEndDate()).collect(Collectors.toList());
//			
//			for(LocalDate date : listOfDates) {
//				boolean isKeyPresent = sessions.containsKey(date);
//				StudySession newSession = new StudySession();
//				newSession.setObligation(obligation);
//				newSession.setDateAndTime(date.atStartOfDay());
//				
//				if(isKeyPresent) {
//					List<StudySession> exList = sessions.get(date);
//					exList.add(newSession);
//					sessions.put(date, exList);
//					
//				} else {
//					sessions.put(date, Arrays.asList(newSession));
//				}
//			}
//		}
//		
//		System.out.print(sessions);
//		
//		cal.setSessions(getListFromMap(sessions));
//		return cal;
//	}
//	
	
	private List<StudySession> getListFromMap(Map<LocalDate, List<StudySession>> sessions) {
		List<StudySession> lista = new ArrayList<>();
		
		for(LocalDate date : sessions.keySet()) {
			lista.addAll(sessions.get(date));
		}
		return lista;
	}

	private Map<LocalDate, List<StudySession>> getMapFromList(List<StudySession> lista) {
		Map<LocalDate, List<StudySession>> mapa = new HashMap<>();
		
		for(StudySession s : lista) {
			LocalDate date = s.getDateAndTime().toLocalDate();
			boolean isKeyPresent = mapa.containsKey(date);
			if(!isKeyPresent) {
				mapa.put(date, Arrays.asList(s));
			}else {
				List<StudySession> sesije = mapa.get(date);
				sesije.add(s);
				mapa.put(date, sesije);
			}
		}
		return mapa;
	}

	//CALCULATE PRIORITIES
	public StudyCalendar calculatePriorities(StudyCalendar cal) {
		
		Map<LocalDate, List<StudySession>> dummy = getMapFromList(cal.getSessions());
		
		for(LocalDate date :dummy.keySet()) {
			List<StudySession> sessions = dummy.get(date);
			
			Collections.sort(sessions, new SortByPriority());
			int i = 1;
			for (StudySession s : sessions) {
				s.setPriority(i);
				i++;
			}
			
			dummy.put(date, sessions);
		}
		
		cal.setSessions(getListFromMap(dummy));
		return cal;
	}
	
	class SortByPriority implements Comparator<StudySession> {
		 
	    public int compare(StudySession a, StudySession b)
	    {
	 
	        return  (int) (a.getObligation().getMaxPoints() - b.getObligation().getMaxPoints());
	    }
	}
	

	
	//CREATE SESSIONS
	public StudyCalendar createSessions(StudyCalendar cal) {
		
		//podelimo broj sati na broj dana
		List<Obligation> obs= new ArrayList<>(cal.getObligations());
		Map<LocalDate, List<StudySession>> sess= getMapFromList(cal.getSessions());
		
		Collections.sort(obs, new SortByAvg());
		
		
		//po tom rasporedu- krenemo
		//rasporedimo sesije ne manje od 2 sata redom svaki dan, i zaokruzene na vise od onog proseka
		for(Obligation o: obs) {
			int avgHrsPerDay = o.getStudyHours()/ (o.getStudyStartDate().datesUntil(o.getStudyEndDate()).collect(Collectors.toList())).size();
			int hoursPerDay = avgHrsPerDay;
			if(avgHrsPerDay < 2) {
				hoursPerDay = 2;
			}
			
			int current = o.getStudyHours();
			for(LocalDate date : o.getStudyStartDate().datesUntil(o.getStudyEndDate()).collect(Collectors.toList())) {
				if (current<= 0) {
					sess = clearEmptySessions(sess,o);
				}
				//provera da li je taj dan zauzet?
				List<StudySession> exList = sess.get(date); //lista sesija
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
		cal.setSessions(getListFromMap(sess));
		return cal;
	}
	
	private Map<LocalDate, List<StudySession>> clearEmptySessions(Map<LocalDate, List<StudySession>> sessions, Obligation o) {
		//sve sesije koje su za tu obligaciju i prazne su, izbrisi
		Map<LocalDate, List<StudySession>> sess=   new HashMap<>(sessions);
		
		for(LocalDate date : sessions.keySet()) {
			List<StudySession> lista = sessions.get(date);
			for(StudySession s: lista) {
				if(s.getObligation() == o && (s.getDurationInHours() == 0)) {
					//U NASOJ DUMMY LISTI BRISEMO
					sess.get(date).remove(s); //TODO ili sa indexOf
				}
			}
		}
		return sess;
	}

	class SortByAvg implements Comparator<Obligation> {
		 
	    public int compare(Obligation a, Obligation b)
	    {
	        return  (int) (a.getStudyHours()/ (a.getStudyStartDate().datesUntil(a.getStudyEndDate())).count()
	        		- b.getStudyHours()/ (b.getStudyStartDate().datesUntil(b.getStudyEndDate())).count());
	    }
	}
}
