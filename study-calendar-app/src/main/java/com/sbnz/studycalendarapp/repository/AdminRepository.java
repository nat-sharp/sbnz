package com.sbnz.studycalendarapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.studycalendarapp.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	public Admin findByUsername(String username);
}
