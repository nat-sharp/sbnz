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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sbnz.studycalendarapp.enums.StudentActivity;
import com.sbnz.studycalendarapp.enums.StudentCategory;

@Entity
@Table(name="student")
public class Student implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="concentrated_study_hours")
	private boolean concetratedStudyHours;
	
	@Column(name="category")
	private StudentCategory category;
	
	@Column(name="activity_points")
	private Double activityPoints;
	
	@Column(name="activity")
	private StudentActivity activity;
	
//	private List<Day> studyDays; TODO
//	private List<PartOfDay> partsOfStudyDays; TODO
	
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Subject> subjects;
	
//	@OneToOne(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private StudyCalendar studyCalendar;
	
	
	public Student() {
		super();
	}

	public Student(Integer id, String firstName, String lastName, String username, String password,
			boolean concetratedStudyHours, StudentCategory category, Double activityPoints, StudentActivity activity,
			List<Subject> subjects) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.concetratedStudyHours = concetratedStudyHours;
		this.category = category;
		this.activityPoints = activityPoints;
		this.activity = activity;
		this.subjects = subjects;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public boolean isConcetratedStudyHours() {
		return concetratedStudyHours;
	}

	public void setConcetratedStudyHours(boolean concetratedStudyHours) {
		this.concetratedStudyHours = concetratedStudyHours;
	}

	public StudentCategory getCategory() {
		return category;
	}

	public void setCategory(StudentCategory category) {
		this.category = category;
	}

	public Double getActivityPoints() {
		return activityPoints;
	}

	public void setActivityPoints(Double activityPoints) {
		this.activityPoints = activityPoints;
	}

	public StudentActivity getActivity() {
		return activity;
	}

	public void setActivity(StudentActivity activity) {
		this.activity = activity;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
//	public List<Day> getStudyDays() {
//	return studyDays;
//}
//public void setStudyDays(List<Day> studyDays) {
//	this.studyDays = studyDays;
//}
//public List<PartOfDay> getPartsOfStudyDays() {
//	return partsOfStudyDays;
//}
//public void setPartsOfStudyDays(List<PartOfDay> partsOfStudyDays) {
//	this.partsOfStudyDays = partsOfStudyDays;
//}

//public StudyCalendar getStudyCalendar() {
//	return studyCalendar;
//}
//public void setStudyCalendar(StudyCalendar studyCalendar) {
//	this.studyCalendar = studyCalendar;
//}

	

	@Override
	public int hashCode() {
		return Objects.hash(activity, activityPoints, category, concetratedStudyHours, firstName, id, lastName,
				password, subjects, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return activity == other.activity && Objects.equals(activityPoints, other.activityPoints)
				&& category == other.category && concetratedStudyHours == other.concetratedStudyHours
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(subjects, other.subjects) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", concetratedStudyHours=" + concetratedStudyHours + ", category="
				+ category + ", activityPoints=" + activityPoints + ", activity=" + activity + ", subjects=" + subjects
				+ "]";
	}

}
