//package com.sbnz.studycalendarapp.model;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Objects;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="study_calendar")
//public class StudyCalendar implements Serializable{
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Integer id;
//	
//	@OneToOne(fetch=FetchType.LAZY)
//	private Student student;
//	
//	@OneToMany(mappedBy="studyCalendar", fetch=FetchType.LAZY, cascade= CascadeType.ALL)
//	private List<StudySession> studySessions;
//	
//	
//	public StudyCalendar() {
//		super();
//	}
//	
//	public StudyCalendar(Integer id, Student student, List<StudySession> studySessions) {
//		super();
//		this.id = id;
//		this.student = student;
//		this.studySessions = studySessions;
//	}
//
//	public Student getStudent() {
//		return student;
//	}
//	
//	public void setStudent(Student student) {
//		this.student = student;
//	}
//	
//	public List<StudySession> getStudySessions() {
//		return studySessions;
//	}
//	
//	public void setStudySessions(List<StudySession> studySessions) {
//		this.studySessions = studySessions;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id, student, studySessions);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		StudyCalendar other = (StudyCalendar) obj;
//		return Objects.equals(id, other.id) && Objects.equals(student, other.student)
//				&& Objects.equals(studySessions, other.studySessions);
//	}
//
//
//	@Override
//	public String toString() {
//		return "StudyCalendar [id=" + id + ", student=" + student + ", studySessions=" + studySessions + "]";
//	}
//
//}
