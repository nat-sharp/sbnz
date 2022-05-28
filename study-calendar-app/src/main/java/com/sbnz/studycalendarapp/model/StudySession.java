package com.sbnz.studycalendarapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class StudySession implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LocalDateTime dateAndTime;
	private boolean done;
	private Obligation obligation;
	
	
	public StudySession() {
		super();
	}
	
	public StudySession(LocalDateTime dateAndTime, boolean done, Obligation obligation) {
		super();
		this.dateAndTime = dateAndTime;
		this.done = done;
		this.obligation = obligation;
	}
	
	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}
	
	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
	
	public Obligation getObligation() {
		return obligation;
	}
	
	public void setObligation(Obligation obligation) {
		this.obligation = obligation;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dateAndTime, done, obligation);
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
		return Objects.equals(dateAndTime, other.dateAndTime) && done == other.done
				&& Objects.equals(obligation, other.obligation);
	}
	
	@Override
	public String toString() {
		return "StudySession [dateAndTime=" + dateAndTime + ", done=" + done + ", obligation=" + obligation + "]";
	}
	
	
}
