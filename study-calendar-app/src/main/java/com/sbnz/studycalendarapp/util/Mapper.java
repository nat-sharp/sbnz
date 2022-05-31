package com.sbnz.studycalendarapp.util;

import com.sbnz.studycalendarapp.dto.CreateObligationDto;
import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.repository.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

	@Autowired
	private SubjectRepository subjectRepository;
	
	public Obligation toObligation(CreateObligationDto dto) {
		Obligation o = new Obligation();
		o.setName(dto.getName());
		o.setDateAndTime(dto.getDateAndTime());
		o.setStudyStartDate(dto.getStudyStartDate());
		o.setStudyEndDate(dto.getStudyEndDate());
		o.setStudyHours(dto.getStudyHours());
		o.setObligationType(dto.getType());
		o.setMaxPoints(dto.getMaxPoints());
		o.setCorrigible(dto.getCorrigible());
		o.setSubject(subjectRepository.getById(dto.getSubjectId()));
		return o;
	}
}

