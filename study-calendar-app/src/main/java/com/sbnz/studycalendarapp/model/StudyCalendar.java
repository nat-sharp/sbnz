package com.sbnz.studycalendarapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="study_calendar")
public class StudyCalendar implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Student student;
	
	@OneToMany(mappedBy="studyCalendar", fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	private List<StudySession> sessions;  //resenje je da sesija ima svoj datum, a kad obradjujem podatke- stvorim sebi mapu
	
	@OneToMany(mappedBy="studyCalendar", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Obligation> obligations;
	
	@Column(name="obligations_injected")
	private Boolean obligationsInjected;
	
	@Column(name="priorities_calculated")
	private Boolean prioritiesCalculated;
	
	@Column(name="sessions_created")
	private Boolean sessionsCreated;
	
	
	public StudyCalendar() {
		super();
		this.obligationsInjected = false;
		this.sessionsCreated = false;
		this.prioritiesCalculated = false;
		this.sessions = new ArrayList<>();
		this.obligations = new ArrayList<>();
	}


	public StudyCalendar(Integer id, Student student, List<StudySession> organizedSessions,
			List<Obligation> obligations, Boolean obligationsInjected, Boolean prioritiesCalculated,
			Boolean sessionsCreated) {
		super();
		this.id = id;
		this.student = student;
		this.sessions = organizedSessions;
		this.obligations = obligations;
		this.obligationsInjected = obligationsInjected;
		this.prioritiesCalculated = prioritiesCalculated;
		this.sessionsCreated = sessionsCreated;
	}

	public List<Obligation> getObligations() {
		return obligations;
	}

	public void setObligations(List<Obligation> obligations) {
		this.obligations = obligations;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public List<StudySession> getSessions() {
		return sessions;
	}


	public void setSessions(List<StudySession> sessions) {
		this.sessions = sessions;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}


	public Boolean getObligationsInjected() {
		return obligationsInjected;
	}


	public void setObligationsInjected(Boolean obligationsInjected) {
		this.obligationsInjected = obligationsInjected;
	}


	public Boolean getPrioritiesCalculated() {
		return prioritiesCalculated;
	}


	public void setPrioritiesCalculated(Boolean prioritiesCalculated) {
		this.prioritiesCalculated = prioritiesCalculated;
	}


	public Boolean getSessionsCreated() {
		return sessionsCreated;
	}


	public void setSessionsCreated(Boolean sessionsCreated) {
		this.sessionsCreated = sessionsCreated;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, obligationsInjected, prioritiesCalculated, sessionsCreated,
				student);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudyCalendar other = (StudyCalendar) obj;
		return Objects.equals(id, other.id) && Objects.equals(obligations, other.obligations)
				&& Objects.equals(obligationsInjected, other.obligationsInjected)
				&& Objects.equals(prioritiesCalculated, other.prioritiesCalculated)
				&& Objects.equals(sessions, other.sessions) && Objects.equals(sessionsCreated, other.sessionsCreated)
				&& Objects.equals(student, other.student);
	}


	@Override
	public String toString() {
		return "StudyCalendar [id=" + id + ", student=IZBACILA"+ ", sessions=IZBACILA" + ", obligations=IZBACILA"
				+ ", obligationsInjected=" + obligationsInjected + ", prioritiesCalculated="
				+ prioritiesCalculated + ", sessionsCreated=" + sessionsCreated + "]";
	}







}
