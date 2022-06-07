package com.sbnz.studycalendarapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.studycalendarapp.model.Student;
import com.sbnz.studycalendarapp.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{
	public Subject findOneById(Integer id);
	
	public Subject findOneByName(String name);

	public List<Subject> findAllByStudent(Student student);
}
