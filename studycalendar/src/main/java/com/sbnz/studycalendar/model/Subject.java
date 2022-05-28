package com.sbnz.studycalendar.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subject {
	private String name;
	private boolean passed;
	private int grade;
	private Student student;
	private List<Obligation> obligations;
}
