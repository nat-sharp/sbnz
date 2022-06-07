package com.sbnz.studycalendarapp.dto;

public class SubjectDto {
	private String name;
	private Double earnedPoints;
	private boolean passed;
	private int grade;
	private boolean finished;
	
	public SubjectDto() {
		
	}

	public SubjectDto(String name, Double earnedPoints, boolean passed, int grade, boolean finished) {
		super();
		this.name = name;
		this.earnedPoints = earnedPoints;
		this.passed = passed;
		this.grade = grade;
		this.finished = finished;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
