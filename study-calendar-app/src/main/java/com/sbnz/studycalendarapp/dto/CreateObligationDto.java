package com.sbnz.studycalendarapp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sbnz.studycalendarapp.enums.ObligationType;

public class CreateObligationDto {
	
	private Boolean corrigible;
	private LocalDateTime dateAndTime;
	private Double maxPoints;
	private String name;
	private ObligationType type;
	private LocalDate studyStartDate;
	private LocalDate studyEndDate;
	private int studyHours;
	private Integer subjectId;
	
	
	
	public CreateObligationDto() {
		super();
	}

	public CreateObligationDto(Boolean corrigible, LocalDateTime dateAndTime, Double maxPoints, String name,
			ObligationType type, LocalDate studyStartDate, LocalDate studyEndDate, int studyHours, Integer subjectId) {
		super();
		this.corrigible = corrigible;
		this.dateAndTime = dateAndTime;
		this.maxPoints = maxPoints;
		this.name = name;
		this.type = type;
		this.studyStartDate = studyStartDate;
		this.studyEndDate = studyEndDate;
		this.studyHours = studyHours;
		this.subjectId = subjectId;
	}

	public Boolean getCorrigible() {
		return corrigible;
	}

	public void setCorrigible(Boolean corrigible) {
		this.corrigible = corrigible;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Double getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(Double maxPoints) {
		this.maxPoints = maxPoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObligationType getType() {
		return type;
	}

	public void setType(ObligationType type) {
		this.type = type;
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

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	
	
}
