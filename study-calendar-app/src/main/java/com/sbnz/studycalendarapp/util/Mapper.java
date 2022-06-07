package com.sbnz.studycalendarapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.studycalendarapp.dto.CreateObligationDto;
import com.sbnz.studycalendarapp.dto.ObligationDto;
import com.sbnz.studycalendarapp.dto.StudentDto;
import com.sbnz.studycalendarapp.dto.SubjectDto;
import com.sbnz.studycalendarapp.enums.StudentActivity;
import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.model.Subject;
import com.sbnz.studycalendarapp.repository.SubjectRepository;

@Service
public class Mapper {

	@Autowired
	private SubjectRepository subjectRepository;

	public Student toStudent(StudentDto dto) {
		Student s = new Student();
		s.setFirstName(dto.getFirstName());
		s.setLastName(dto.getLastName());
		s.setUsername(dto.getUsername());
		s.setPassword(dto.getPassword());
		s.setConcetratedStudyHours(dto.isConcentratedStudyHours());
		s.setCategory(dto.getCategory());
		s.setActivity(StudentActivity.BEGGINER);
		s.setActivityPoints(0.0);
		return s;
	}
	
	public SubjectDto toSubjectDto(Subject s) {
		return new SubjectDto(s.getName(), s.getEarnedPoints(), s.isPassed(), s.getGrade(), s.isFinished());
	}

	public ObligationDto toObligationDto(Obligation o) {
		return new ObligationDto(o.getName(), o.getDateAndTime(), o.getPriority(), o.getObligationType(), o.getMaxPoints(), o.getEarnedPoints(), o.isSkipped(), o.isPassed(), o.isFinished(), o.isCorrigible());
	}
	
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

