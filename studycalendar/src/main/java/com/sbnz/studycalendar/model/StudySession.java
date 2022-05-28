package com.sbnz.studycalendar.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudySession {
	private LocalDateTime dateAndTime;
	private boolean done;
	private Obligation obligation;
}
