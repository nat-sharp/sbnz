package com.sbnz.studycalendarapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sbnz.studycalendarapp.dto.CreateObligationDto;
import com.sbnz.studycalendarapp.enums.ObligationType;

@Entity
@Table(name="obligation")
public class Obligation implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="date_and_time")
	private LocalDateTime dateAndTime;
	
	@Column(name="study_start_date")
	private LocalDate studyStartDate;
	
	@Column(name="study_end_date")
	private LocalDate studyEndDate;
	
	@Column(name="study_hours")
	private int studyHours;
	
	@Column(name="priority")
	private int priority;
	
	@Column(name="obligation_type")
	private ObligationType obligationType;
	
	@Column(name="max_points")
	private Double maxPoints;
	
	@Column(name="earned_points")
	private Double earnedPoints;
	
	@Column(name="skipped")
	private boolean skipped;
	
	@Column(name="passed")
	private boolean passed;
	
	@Column(name="finished")
	private boolean finished;
	
	@Column(name="corrigible")
	private boolean corrigible;
	
	@ManyToOne(fetch= FetchType.LAZY)
	private Subject subject;
	
	@OneToMany(mappedBy = "obligation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<StudySession> studySessions;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private StudyCalendar studyCalendar;
	
	
	public Obligation() {
		super();
	}


	public Obligation(Integer id, String name, LocalDateTime dateAndTime, LocalDate studyStartDate,
			LocalDate studyEndDate, int studyHours, int priority, ObligationType obligationType, Double maxPoints,
			Double earnedPoints, boolean skipped, boolean passed, boolean finished, boolean corrigible, Subject subject,
			List<StudySession> studySessions, StudyCalendar studyCalendar) {
		super();
		this.id = id;
		this.name = name;
		this.dateAndTime = dateAndTime;
		this.studyStartDate = studyStartDate;
		this.studyEndDate = studyEndDate;
		this.studyHours = studyHours;
		this.priority = priority;
		this.obligationType = obligationType;
		this.maxPoints = maxPoints;
		this.earnedPoints = earnedPoints;
		this.skipped = skipped;
		this.passed = passed;
		this.finished = finished;
		this.corrigible = corrigible;
		this.subject = subject;
		this.studySessions = studySessions;
		this.studyCalendar = studyCalendar;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}


	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}


	public LocalDate getStudyStartDate() {
		return studyStartDate;
	}


	public void setStudyStartDate(LocalDate studyStartDate) {
		this.studyStartDate = studyStartDate;
	}


	public LocalDate getStudyEndDate() {
		return studyEndDate;
	}


	public void setStudyEndDate(LocalDate studyEndDate) {
		this.studyEndDate = studyEndDate;
	}


	public int getStudyHours() {
		return studyHours;
	}


	public void setStudyHours(int studyHours) {
		this.studyHours = studyHours;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public ObligationType getObligationType() {
		return obligationType;
	}


	public void setObligationType(ObligationType obligationType) {
		this.obligationType = obligationType;
	}


	public Double getMaxPoints() {
		return maxPoints;
	}


	public void setMaxPoints(Double maxPoints) {
		this.maxPoints = maxPoints;
	}


	public Double getEarnedPoints() {
		return earnedPoints;
	}


	public void setEarnedPoints(Double earnedPoints) {
		this.earnedPoints = earnedPoints;
	}


	public boolean isSkipped() {
		return skipped;
	}


	public void setSkipped(boolean skipped) {
		this.skipped = skipped;
	}


	public boolean isPassed() {
		return passed;
	}


	public void setPassed(boolean passed) {
		this.passed = passed;
	}


	public boolean isFinished() {
		return finished;
	}


	public void setFinished(boolean finished) {
		this.finished = finished;
	}


	public boolean isCorrigible() {
		return corrigible;
	}


	public void setCorrigible(boolean corrigible) {
		this.corrigible = corrigible;
	}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public List<StudySession> getStudySessions() {
		return studySessions;
	}


	public void setStudySessions(List<StudySession> studySessions) {
		this.studySessions = studySessions;
	}


	public StudyCalendar getStudyCalendar() {
		return studyCalendar;
	}


	public void setStudyCalendar(StudyCalendar studyCalendar) {
		this.studyCalendar = studyCalendar;
	}


	@Override
	public int hashCode() {
		return Objects.hash(corrigible, dateAndTime, earnedPoints, finished, id, maxPoints, name, obligationType,
				passed, priority, skipped, studyCalendar, studyEndDate, studyHours, studySessions, studyStartDate,
				subject);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Obligation other = (Obligation) obj;
		return corrigible == other.corrigible && Objects.equals(dateAndTime, other.dateAndTime)
				&& Objects.equals(earnedPoints, other.earnedPoints) && finished == other.finished
				&& Objects.equals(id, other.id) && Objects.equals(maxPoints, other.maxPoints)
				&& Objects.equals(name, other.name) && obligationType == other.obligationType && passed == other.passed
				&& priority == other.priority && skipped == other.skipped
				&& Objects.equals(studyCalendar, other.studyCalendar)
				&& Objects.equals(studyEndDate, other.studyEndDate) && studyHours == other.studyHours
				&& Objects.equals(studySessions, other.studySessions)
				&& Objects.equals(studyStartDate, other.studyStartDate) && Objects.equals(subject, other.subject);
	}


	@Override
	public String toString() {
		return "Obligation [id=" + id + ", name=" + name + ", dateAndTime=" + dateAndTime + ", studyStartDate="
				+ studyStartDate + ", studyEndDate=" + studyEndDate + ", studyHours=" + studyHours + ", priority="
				+ priority + ", obligationType=" + obligationType + ", maxPoints=" + maxPoints + ", earnedPoints="
				+ earnedPoints + ", skipped=" + skipped + ", passed=" + passed + ", finished=" + finished
				+ ", corrigible=" + corrigible + ", subject=" + subject + ", studySessions=" + studySessions
				+ ", studyCalendar=" + studyCalendar + "]";
	}
	
	



}
