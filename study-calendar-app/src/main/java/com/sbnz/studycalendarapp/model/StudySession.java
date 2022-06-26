package com.sbnz.studycalendarapp.model;

import java.io.Serializable;
import java.time.LocalDate;
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
	
	@Column(name="date")
	private LocalDate date;
	
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

	public StudySession(LocalDate datum, StudyCalendar sc, Obligation o) {
		super();
		this.id = null;
		this.date = datum;
		this.durationInHours = 0;
		this.isDone = false;
		this.obligation = o;
		this.studyCalendar = sc;
		this.priority = 0;
		
	}
	
	public StudySession(StudySession orig, int priority) {
		this.id = orig.getId();
		this.date = orig.getDate();
		this.durationInHours = orig.getDurationInHours();
		this.isDone = orig.isDone();
		this.obligation = orig.getObligation();
		this.studyCalendar = orig.getStudyCalendar();
		
		this.priority = priority;
	}
	
	public StudySession(Integer id, LocalDate date, float durationInHours, boolean isDone,
			Obligation obligation, StudyCalendar studyCalendar, int priority) {
		super();
		this.id = id;
		this.date = date;
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


	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
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
		return "StudySession [id=" + id + ", date=" + date + ", durationInHours=" + durationInHours + ", isDone="
				+ isDone + ", priority=" + priority
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, durationInHours, id, isDone, priority);
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
		return Objects.equals(date, other.date)
				&& Float.floatToIntBits(durationInHours) == Float.floatToIntBits(other.durationInHours)
				&& Objects.equals(id, other.id) && isDone == other.isDone
				&& Objects.equals(obligation, other.obligation) && priority == other.priority
				&& Objects.equals(studyCalendar, other.studyCalendar);
	}

	public SessionDto toDto() {
		SessionDto dto = new SessionDto();
		dto.setId(id);
		dto.setDate(date);
		dto.setDurationInHours(durationInHours);
		dto.setPriority(priority);
		dto.setObligationname(obligation.getName());
		
		return dto;
	}
	
}
