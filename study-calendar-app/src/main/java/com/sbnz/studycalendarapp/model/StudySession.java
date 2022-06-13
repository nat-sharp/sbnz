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

import com.sbnz.studycalendarapp.dto.SessionDto;


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
	
	@Column(name="priority")
	private int priority;
	
	public StudySession() {
		super();
	}

	public StudySession(Integer id, LocalDateTime dateAndTime, float durationInHours, boolean isDone,
			Obligation obligation, StudyCalendar studyCalendar, int priority) {
		super();
		this.id = id;
		this.dateAndTime = dateAndTime;
		this.durationInHours = durationInHours;
		this.isDone = isDone;
		this.obligation = obligation;
		this.studyCalendar = studyCalendar;
		this.priority = priority;
	}


	public StudyCalendar getStudyCalendar() {
		return studyCalendar;
	}

	public void setStudyCalendar(StudyCalendar studyCalendar) {
		this.studyCalendar = studyCalendar;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
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
	public int hashCode() {
		return Objects.hash(dateAndTime, durationInHours, id, isDone, obligation, priority, studyCalendar);
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
		return Objects.equals(dateAndTime, other.dateAndTime)
				&& Float.floatToIntBits(durationInHours) == Float.floatToIntBits(other.durationInHours)
				&& Objects.equals(id, other.id) && isDone == other.isDone
				&& Objects.equals(obligation, other.obligation) && priority == other.priority
				&& Objects.equals(studyCalendar, other.studyCalendar);
	}

	@Override
	public String toString() {
		return "StudySession [id=" + id + ", dateAndTime=" + dateAndTime + ", durationInHours=" + durationInHours
				+ ", isDone=" + isDone + ", obligation=IZBACILA" + ", studyCalendar=IZBACILA"
				+ ", priority=" + priority + "]";
	}

	public SessionDto toDto() {
		SessionDto dto = new SessionDto();
		dto.setId(id);
		dto.setDateAndTime(dateAndTime);
		dto.setDurationInHours(durationInHours);
		dto.setPriority(priority);
		dto.setObligationname(obligation.getName());
		
		return dto;
	}

	
	
}
