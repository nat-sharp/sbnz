package com.sbnz.studycalendarapp.repository;

import com.sbnz.studycalendarapp.model.Obligation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ObligationRepository extends JpaRepository<Obligation, Integer>{

}
