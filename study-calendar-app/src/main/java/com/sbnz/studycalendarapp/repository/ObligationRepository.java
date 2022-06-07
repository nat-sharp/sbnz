package com.sbnz.studycalendarapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.studycalendarapp.model.Obligation;
import com.sbnz.studycalendarapp.model.Subject;

public interface ObligationRepository extends JpaRepository<Obligation, Integer>{
	public Obligation findOneById(Integer id);
	
	public List<Obligation> findAllBySubject(Subject subject);
}
