package com.sbnz.studycalendarapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Subject implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private boolean passed;
	private int grade;
	private Student student;
	private List<Obligation> obligations;
	
	
	public Subject() {
		super();
	}
	
	public Subject(String name, boolean passed, int grade) {
		super();
		this.name = name;
		this.passed = passed;
		this.grade = grade;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(grade, name, obligations, passed, student);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return grade == other.grade && Objects.equals(name, other.name)
				&& Objects.equals(obligations, other.obligations) && passed == other.passed
				&& Objects.equals(student, other.student);
	}
	
	@Override
	public String toString() {
		return "Subject [name=" + name + ", passed=" + passed + ", grade=" + grade + ", student=" + student
				+ ", obligations=" + obligations + "]";
	}
	
	
}
