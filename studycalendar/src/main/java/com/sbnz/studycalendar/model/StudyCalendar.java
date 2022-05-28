package com.sbnz.studycalendar.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyCalendar {
	private Student student;
	private List<StudySession> studySessions;
}
