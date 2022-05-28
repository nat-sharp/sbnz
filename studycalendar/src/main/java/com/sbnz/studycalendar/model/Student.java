package com.sbnz.studycalendar.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
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
}
