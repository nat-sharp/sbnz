package com.sbnz.studycalendarapp.model;

import org.kie.api.definition.type.Position;

public class Fact {

	@Position(0)
	private String state1;
	
	@Position(1)
	private String state2;
	
	public Fact() {
		
	}
	
	public Fact(String state1, String state2) {
		super();
		this.state1 = state1;
		this.state2 = state2;
	}
	
	public String getState1() {
		return state1;
	}
	
	public void setState1(String state) {
		this.state1 = state;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}
	
}
