package com.sbnz.studycalendar.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Obligation {
	private String name;
	private LocalDate deadline;
	private int studyHours;
	private int priority;
	private ObligationType obligationType;
	private boolean teamWork;
	private float maxPoints;
	private float earnedPoints;
	private boolean passed;
	private boolean corrigible;
	private Subject subject;
	private List<StudySession> studySessions;
}
