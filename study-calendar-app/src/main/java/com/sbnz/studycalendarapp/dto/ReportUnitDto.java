package com.sbnz.studycalendarapp.dto;

public class ReportUnitDto {
	private int id;
	private String studentName;
	private int studentId;
	private String category;
	private String status;
	private float points;
	
	public ReportUnitDto(String studentName, int studentId, String category, String status, float points) {
		super();
		this.id = 5;
		this.studentName = studentName;
		this.studentId = studentId;
		this.category = category;
		this.status = status;
		this.points = points;
	}
	public ReportUnitDto() {
		super();
	}
	public String getStudentName() {
		return studentName;
	}
	public float getPoints() {
		return points;
	}
	public void setPoints(float points) {
		this.points = points;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
