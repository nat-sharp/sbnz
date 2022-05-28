package com.sbnz.studycalendar.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Subject implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String name;
	private boolean passed;
	private int grade;
	private Student student;
	private List<Obligation> obligations;
}
