package com.sbnz.studycalendarapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.studycalendarapp.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	public Student findByUsername(String username);
}
