package com.sbnz.studycalendarapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="subject")
public class Subject implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;

	@Column(name="earned_points")
	private Double earnedPoints;
	
	@Column(name="passed")
	private boolean passed;
	
	@Column(name="grade")
	private int grade;
	
	@Column(name="finished")
	private boolean finished;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Student student;
	
	@OneToMany(mappedBy="subject", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Obligation> obligations;
	
	public Subject() {
		super();
	}
	
	public Subject(int id, String name, Double earnedPoints, boolean passed, int grade, boolean finished, Student student, List<Obligation> obligations) {
		super();
		this.id = id;
		this.name = name;
		this.earnedPoints = earnedPoints;
		this.passed = passed;
		this.grade = grade;
		this.finished = finished;
		this.student = student;
		this.obligations = obligations;
	}

	public Subject(int id, String name, boolean passed, int grade) {
		super();
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Obligation> getObligations() {
		return obligations;
	}

	public void setObligations(List<Obligation> obligations) {
		this.obligations = obligations;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", earnedPoints=" + earnedPoints + ", passed=" + passed + ", grade=" + grade 
				+ ", finished=" + finished + ", student=" + student + ", obligations=" + obligations + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(grade, id, name, obligations, passed, student, earnedPoints, finished);
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
		return grade == other.grade && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(obligations, other.obligations) && passed == other.passed && finished == other.finished
				&& Objects.equals(student, other.student) && earnedPoints == other.earnedPoints;
	}
}
