package com.sbnz.studycalendarapp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessionDto {

	private Integer id;
	private LocalDate date;
	private float durationInHours;
	private String obligationname;
	private int priority;

	public SessionDto() {
		super();
	}	
	
	
	public SessionDto(Integer id, LocalDate date, float durationInHours, String obligationname,
			int priority) {
		super();
		this.id = id;
		this.date = date;
		this.durationInHours = durationInHours;
		this.obligationname = obligationname;
		this.priority = priority;
	}




	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getDateAndTime() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public float getDurationInHours() {
		return durationInHours;
	}
	public void setDurationInHours(float durationInHours) {
		this.durationInHours = durationInHours;
	}
	public String getObligationname() {
		return obligationname;
	}
	public void setObligationname(String obligationname) {
		this.obligationname = obligationname;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	

}
