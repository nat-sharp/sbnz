package com.sbnz.studycalendarapp.model;

import java.io.Serializable;
import java.time.LocalDate;
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

import com.sbnz.studycalendarapp.enums.ObligationType;

@Entity
@Table(name="obligation")
public class Obligation implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="date_and_time")
	private LocalDate dateAndTime;
	
	@Column(name="study_start_date")
	private LocalDate studyStartDate;
	
	@Column(name="study_end_date")
	private LocalDate studyDateFinish;
	
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
	
	@Column(name="passed")
	private boolean passed;
	
	@Column(name="corrigible")
	private boolean corrigible;
	
	@ManyToOne(fetch= FetchType.LAZY)
	private Subject subject;
	
	@OneToMany(mappedBy = "obligation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<StudySession> studySessions;
	
	
	public Obligation() {
		super();
	}
	
	public Obligation(Integer id, String name, LocalDate dateAndTime, LocalDate studyStartDate,
			LocalDate studyDateFinish, int studyHours, int priority, ObligationType obligationType, Double maxPoints,
			Double earnedPoints, boolean passed, boolean corrigible, Subject subject,
			List<StudySession> studySessions) {
		super();
		this.id = id;
		this.name = name;
		this.dateAndTime = dateAndTime;
		this.studyStartDate = studyStartDate;
		this.studyDateFinish = studyDateFinish;
		this.studyHours = studyHours;
		this.priority = priority;
		this.obligationType = obligationType;
		this.maxPoints = maxPoints;
		this.earnedPoints = earnedPoints;
		this.passed = passed;
		this.corrigible = corrigible;
		this.subject = subject;
		this.studySessions = studySessions;
	}
	
	public LocalDate getStudyStartDate() {
		return studyStartDate;
	}

	public void setStudyStartDate(LocalDate studyStartDate) {
		this.studyStartDate = studyStartDate;
	}

	public LocalDate getStudyDateFinish() {
		return studyDateFinish;
	}

	public void setStudyDateFinish(LocalDate studyDateFinish) {
		this.studyDateFinish = studyDateFinish;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDate dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDeadline() {
		return dateAndTime;
	}
	public void setDeadline(LocalDate deadline) {
		this.dateAndTime = deadline;
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
	public boolean isPassed() {
		return passed;
	}
	public void setPassed(boolean passed) {
		this.passed = passed;
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

	@Override
	public int hashCode() {
		return Objects.hash(corrigible, dateAndTime, earnedPoints, id, maxPoints, name, obligationType, passed,
				priority, studyDateFinish, studyHours, studySessions, studyStartDate, subject);
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
				&& Objects.equals(earnedPoints, other.earnedPoints) && Objects.equals(id, other.id)
				&& Objects.equals(maxPoints, other.maxPoints) && Objects.equals(name, other.name)
				&& obligationType == other.obligationType && passed == other.passed && priority == other.priority
				&& Objects.equals(studyDateFinish, other.studyDateFinish) && studyHours == other.studyHours
				&& Objects.equals(studySessions, other.studySessions)
				&& Objects.equals(studyStartDate, other.studyStartDate) && Objects.equals(subject, other.subject);
	}

	@Override
	public String toString() {
		return "Obligation [id=" + id + ", name=" + name + ", dateAndTime=" + dateAndTime + ", studyStartDate="
				+ studyStartDate + ", studyDateFinish=" + studyDateFinish + ", studyHours=" + studyHours + ", priority="
				+ priority + ", obligationType=" + obligationType + ", maxPoints=" + maxPoints + ", earnedPoints="
				+ earnedPoints + ", passed=" + passed + ", corrigible=" + corrigible + ", subject=" + subject
				+ ", studySessions=" + studySessions + "]";
	}



	
}
