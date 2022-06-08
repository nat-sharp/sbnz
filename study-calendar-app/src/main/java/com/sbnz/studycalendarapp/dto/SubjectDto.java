package com.sbnz.studycalendarapp.dto;

public class SubjectDto {
	private Integer id;
	private String name;
	private Double earnedPoints;
	private boolean passed;
	private int grade;
	private boolean finished;
	
	public SubjectDto() {
		
	}

	public SubjectDto(Integer id, String name, Double earnedPoints, boolean passed, int grade, boolean finished) {
		super();
		this.id = id;
		this.name = name;
		this.earnedPoints = earnedPoints;
		this.passed = passed;
		this.grade = grade;
		this.finished = finished;
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
