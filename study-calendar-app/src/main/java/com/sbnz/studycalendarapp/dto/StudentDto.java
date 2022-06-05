package com.sbnz.studycalendarapp.dto;

import com.sbnz.studycalendarapp.enums.StudentCategory;

public class StudentDto {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean concentratedStudyHours;
	private StudentCategory category;
	
	public StudentDto() {
		
	}
	
	public StudentDto(String firstName, String lastName, String username, String password,
			boolean concentratedStudyHours, StudentCategory category) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.concentratedStudyHours = concentratedStudyHours;
		this.category = category;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isConcentratedStudyHours() {
		return concentratedStudyHours;
	}

	public void setConcentratedStudyHours(boolean concentratedStudyHours) {
		this.concentratedStudyHours = concentratedStudyHours;
	}

	public StudentCategory getCategory() {
		return category;
	}

	public void setCategory(StudentCategory category) {
		this.category = category;
	}
}
