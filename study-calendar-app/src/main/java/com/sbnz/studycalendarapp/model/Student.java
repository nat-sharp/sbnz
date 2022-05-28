package com.sbnz.studycalendarapp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.sbnz.studycalendarapp.enums.Day;
import com.sbnz.studycalendarapp.enums.PartOfDay;
import com.sbnz.studycalendarapp.enums.StudentActivity;
import com.sbnz.studycalendarapp.enums.StudentCategory;

public class Student implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean freeDayBeforeObligation;
	private boolean concetratedStudyHours;
	private StudentCategory category;
	private StudentActivity activity;
	private List<Day> studyDays;
	private List<PartOfDay> partsOfStudyDays;
	private List<Subject> subjects;
	private StudyCalendar studyCalendar;
	public Student() {
		super();
	}
	public Student(String firstName, String lastName, String username, String password, boolean freeDayBeforeObligation,
			boolean concetratedStudyHours, StudentCategory category, StudentActivity activity, List<Day> studyDays,
			List<PartOfDay> partsOfStudyDays, List<Subject> subjects, StudyCalendar studyCalendar) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.freeDayBeforeObligation = freeDayBeforeObligation;
		this.concetratedStudyHours = concetratedStudyHours;
		this.category = category;
		this.activity = activity;
		this.studyDays = studyDays;
		this.partsOfStudyDays = partsOfStudyDays;
		this.subjects = subjects;
		this.studyCalendar = studyCalendar;
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
	public boolean isFreeDayBeforeObligation() {
		return freeDayBeforeObligation;
	}
	public void setFreeDayBeforeObligation(boolean freeDayBeforeObligation) {
		this.freeDayBeforeObligation = freeDayBeforeObligation;
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
	public StudentActivity getActivity() {
		return activity;
	}
	public void setActivity(StudentActivity activity) {
		this.activity = activity;
	}
	public List<Day> getStudyDays() {
		return studyDays;
	}
	public void setStudyDays(List<Day> studyDays) {
		this.studyDays = studyDays;
	}
	public List<PartOfDay> getPartsOfStudyDays() {
		return partsOfStudyDays;
	}
	public void setPartsOfStudyDays(List<PartOfDay> partsOfStudyDays) {
		this.partsOfStudyDays = partsOfStudyDays;
	}
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	public StudyCalendar getStudyCalendar() {
		return studyCalendar;
	}
	public void setStudyCalendar(StudyCalendar studyCalendar) {
		this.studyCalendar = studyCalendar;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(activity, category, concetratedStudyHours, firstName, freeDayBeforeObligation, lastName,
				partsOfStudyDays, password, studyCalendar, studyDays, subjects, username);
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
		return activity == other.activity && category == other.category
				&& concetratedStudyHours == other.concetratedStudyHours && Objects.equals(firstName, other.firstName)
				&& freeDayBeforeObligation == other.freeDayBeforeObligation && Objects.equals(lastName, other.lastName)
				&& Objects.equals(partsOfStudyDays, other.partsOfStudyDays) && Objects.equals(password, other.password)
				&& Objects.equals(studyCalendar, other.studyCalendar) && Objects.equals(studyDays, other.studyDays)
				&& Objects.equals(subjects, other.subjects) && Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password + ", freeDayBeforeObligation=" + freeDayBeforeObligation + ", concetratedStudyHours="
				+ concetratedStudyHours + ", category=" + category + ", activity=" + activity + ", studyDays="
				+ studyDays + ", partsOfStudyDays=" + partsOfStudyDays + ", subjects=" + subjects + ", studyCalendar="
				+ studyCalendar + "]";
	}

	
}
