package com.sbnz.studycalendarapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.sbnz.studycalendarapp.enums.ObligationType;

public class Obligation implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private LocalDate deadline;
	private int studyHours;
	private int priority;
	private ObligationType obligationType;
	private boolean teamWork;
	private float maxPoints;
	private float earnedPoints;
	private boolean passed;
	private boolean corrigible;
	private Subject subject;
	private List<StudySession> studySessions;
	public Obligation() {
		super();
	}
	public Obligation(String name, LocalDate deadline, int studyHours, int priority, ObligationType obligationType,
			boolean teamWork, float maxPoints, float earnedPoints, boolean passed, boolean corrigible, Subject subject,
			List<StudySession> studySessions) {
		super();
		this.name = name;
		this.deadline = deadline;
		this.studyHours = studyHours;
		this.priority = priority;
		this.obligationType = obligationType;
		this.teamWork = teamWork;
		this.maxPoints = maxPoints;
		this.earnedPoints = earnedPoints;
		this.passed = passed;
		this.corrigible = corrigible;
		this.subject = subject;
		this.studySessions = studySessions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
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
	public boolean isTeamWork() {
		return teamWork;
	}
	public void setTeamWork(boolean teamWork) {
		this.teamWork = teamWork;
	}
	public float getMaxPoints() {
		return maxPoints;
	}
	public void setMaxPoints(float maxPoints) {
		this.maxPoints = maxPoints;
	}
	public float getEarnedPoints() {
		return earnedPoints;
	}
	public void setEarnedPoints(float earnedPoints) {
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
		return Objects.hash(corrigible, deadline, earnedPoints, maxPoints, name, obligationType, passed, priority,
				studyHours, studySessions, subject, teamWork);
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
		return corrigible == other.corrigible && Objects.equals(deadline, other.deadline)
				&& Float.floatToIntBits(earnedPoints) == Float.floatToIntBits(other.earnedPoints)
				&& Float.floatToIntBits(maxPoints) == Float.floatToIntBits(other.maxPoints)
				&& Objects.equals(name, other.name) && obligationType == other.obligationType && passed == other.passed
				&& priority == other.priority && studyHours == other.studyHours
				&& Objects.equals(studySessions, other.studySessions) && Objects.equals(subject, other.subject)
				&& teamWork == other.teamWork;
	}
	@Override
	public String toString() {
		return "Obligation [name=" + name + ", deadline=" + deadline + ", studyHours=" + studyHours + ", priority="
				+ priority + ", obligationType=" + obligationType + ", teamWork=" + teamWork + ", maxPoints="
				+ maxPoints + ", earnedPoints=" + earnedPoints + ", passed=" + passed + ", corrigible=" + corrigible
				+ ", subject=" + subject + ", studySessions=" + studySessions + "]";
	}
	
}
