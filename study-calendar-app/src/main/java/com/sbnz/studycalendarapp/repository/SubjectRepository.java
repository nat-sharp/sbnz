package com.sbnz.studycalendarapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.studycalendarapp.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{
	public Subject findOneById(Integer id);
}
