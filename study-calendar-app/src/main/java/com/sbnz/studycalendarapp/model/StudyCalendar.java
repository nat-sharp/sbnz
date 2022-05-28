package com.sbnz.studycalendarapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class StudyCalendar implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Student student;
	private List<StudySession> studySessions;
	
	
	public StudyCalendar() {
		super();
	}
	
	public StudyCalendar(Student student, List<StudySession> studySessions) {
		super();
		this.student = student;
		this.studySessions = studySessions;
	}
	
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
	
	public List<StudySession> getStudySessions() {
		return studySessions;
	}
	
	public void setStudySessions(List<StudySession> studySessions) {
		this.studySessions = studySessions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(student, studySessions);
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
		return Objects.equals(student, other.student) && Objects.equals(studySessions, other.studySessions);
	}

	@Override
	public String toString() {
		return "StudyCalendar [student=" + student + ", studySessions=" + studySessions + "]";
	}
	
	

}
