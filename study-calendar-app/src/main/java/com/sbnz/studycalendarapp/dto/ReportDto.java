package com.sbnz.studycalendarapp.dto;

import java.util.List;

public class ReportDto {

	private List<ReportUnitDto> reportUnits;

	public ReportDto(List<ReportUnitDto> reportUnits) {
		super();
		this.reportUnits = reportUnits;
	}

	public ReportDto() {
		super();
	}

	public List<ReportUnitDto> getReportUnits() {
		return reportUnits;
	}

	public void setReportUnits(List<ReportUnitDto> reportUnits) {
		this.reportUnits = reportUnits;
	}
	
	
}
