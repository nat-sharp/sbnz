package com.sbnz.studycalendarapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.studycalendarapp.model.Admin;
import com.sbnz.studycalendarapp.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository repository;
	
	private static Logger log = LoggerFactory.getLogger(AdminService.class);
	
	public Admin findByUsername(String username) {
		return repository.findByUsername(username);
	}
}
