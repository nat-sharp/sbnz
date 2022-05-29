package com.sbnz.studycalendarapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="study_session")
public class StudySession implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="date_and_time")
	private LocalDateTime dateAndTime;
	
	@Column(name="duration_in_hours")
	private float durationInHours;
	
	@Column(name="is_done")
	private boolean isDone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Obligation obligation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private StudyCalendar studyCalendar;
	
	
	public StudySession() {
		super();
	}

	public StudySession(LocalDateTime dateAndTime, float durationInHours, boolean done, Obligation obligation) {
		super();
		this.dateAndTime = dateAndTime;
		this.durationInHours = durationInHours;
		this.isDone = done;
		this.obligation = obligation;
	}



	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}
	
	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	public void setDone(boolean done) {
		this.isDone = done;
	}
	
	public Obligation getObligation() {
		return obligation;
	}
	
	public void setObligation(Obligation obligation) {
		this.obligation = obligation;
	}

	public float getDurationInHours() {
		return durationInHours;
		
	}

	public void setDurationInHours(float durationInHours) {
		this.durationInHours = durationInHours;
		
	}

	@Override
	public String toString() {
		return "StudySession [dateAndTime=" + dateAndTime + ", durationInHours=" + durationInHours + ", done=" + isDone
				+ ", obligation=" + obligation + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateAndTime, isDone, durationInHours, obligation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudySession other = (StudySession) obj;
		return Objects.equals(dateAndTime, other.dateAndTime) && isDone == other.isDone
				&& Float.floatToIntBits(durationInHours) == Float.floatToIntBits(other.durationInHours)
				&& Objects.equals(obligation, other.obligation);
	}
	
	
	
}
