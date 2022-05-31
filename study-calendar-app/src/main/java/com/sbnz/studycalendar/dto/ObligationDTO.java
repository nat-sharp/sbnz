package com.sbnz.studycalendar.dto;

public class ObligationDTO {
	private Integer id;
	private boolean skipped;
	private Double earnedPoints;
	
	public ObligationDTO(Integer id, boolean skipped, Double earnedPoints) {
		super();
		this.id = id;
		this.skipped = skipped;
		this.earnedPoints = earnedPoints;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public boolean isSkipped() {
		return skipped;
	}
	
	public void setSskipped(boolean skipped) {
		this.skipped = skipped;
	}
	
	public Double getEarnedPoints() {
		return earnedPoints;
	}
	
	public void setEarnedPoints(Double earnedPoints) {
		this.earnedPoints = earnedPoints;
	}
}
