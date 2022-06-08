package com.sbnz.studycalendarapp.dto;

import java.time.LocalDateTime;

import com.sbnz.studycalendarapp.enums.ObligationType;

public class ObligationDto {
	private Integer id;
	private String name;
	private LocalDateTime dateAndTime;
	private int priority;
	private ObligationType obligationType;
	private Double maxPoints;
	private Double earnedPoints;
	private boolean skipped;
	private boolean passed;
	private boolean finished;
	private boolean corrigible;
	
	public ObligationDto() {
		
	}
	
	public ObligationDto(Integer id, String name, LocalDateTime dateAndTime, int priority, ObligationType obligationType,
			Double maxPoints, Double earnedPoints, boolean skipped, boolean passed, boolean finished,
			boolean corrigible) {
		super();
		this.id = id;
		this.name = name;
		this.dateAndTime = dateAndTime;
		this.priority = priority;
		this.obligationType = obligationType;
		this.maxPoints = maxPoints;
		this.earnedPoints = earnedPoints;
		this.skipped = skipped;
		this.passed = passed;
		this.finished = finished;
		this.corrigible = corrigible;
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
}
