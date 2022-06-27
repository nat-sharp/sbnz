package com.sbnz.studycalendarapp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sbnz.studycalendarapp.dto.FinishObligationDto;
import com.sbnz.studycalendarapp.dto.SessionDto;
import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.model.StudyCalendar;
import com.sbnz.studycalendarapp.model.StudySession;
import com.sbnz.studycalendarapp.model.Subject;
import com.sbnz.studycalendarapp.service.StudentService;
import com.sbnz.studycalendarapp.service.StudyCalendarService;
import com.sbnz.studycalendarapp.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/studycal")
public class StudyCalendarController {

	@Autowired
	private StudyCalendarService service;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SubjectService subjectService;

	
	@PostMapping(value="/{username}")
	public ResponseEntity<List<SessionDto>> createSessions(@PathVariable String username) {

		Student student = studentService.findByUsername(username);
		if(student == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		List<Subject> subjects = subjectService.findAllByStudent(student);
		

		List<Obligation> obligations = new ArrayList<>();
		for(Subject s : subjects) {
			obligations.addAll(s.getObligations());
		}
		
		List<StudySession> sessions = service.makeSessions(obligations);
		
		Collections.sort(sessions, new Sorter());
		
		List<SessionDto> dtos = new ArrayList<>();
		for(StudySession s : sessions) {
			dtos.add(s.toDto());
		}
		return new ResponseEntity<List<SessionDto>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value="/{username}")
	public ResponseEntity<List<SessionDto>> getSessionsForStudent(@PathVariable String username) {
		Student student = studentService.findByUsername(username);
		if(student == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		StudyCalendar c = service.getByStudentId(student.getId());
		
		if(c == null || c.getSessions() == null ) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		List<SessionDto> dtos = new ArrayList<>();
		for(StudySession s : c.getSessions()) {
			dtos.add(s.toDto());
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@PutMapping("/setDone/{id}")
	public ResponseEntity<String> finishSession(@PathVariable Integer id) {
		StudySession session = service.getById(id);
		session.setDone(true);
		session = service.save(session);

		if (session != null) {
			return new ResponseEntity<>("Successfully finished session!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/status/{username}")
	public ResponseEntity<String> getStatus(@PathVariable String username) {
		//TODO backward
		return new ResponseEntity<>("You're still a beginner!", HttpStatus.OK);
	}
	
	class Sorter implements Comparator<StudySession>
	{
	    public int compare(StudySession a, StudySession b)
	    {
	        return a.getDate().compareTo(b.getDate());
	    }
	}
}
