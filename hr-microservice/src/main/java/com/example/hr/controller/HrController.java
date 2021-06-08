package com.example.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.boundary.FireEmployeeResponse;
import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.boundary.HireEmployeeResponse;
import com.example.hr.service.HrService;

@RestController
@RequestMapping("/employees")
@RequestScope
@CrossOrigin
public class HrController {
	@Autowired
	private HrService hrService;
	
	@PostMapping
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}

	@DeleteMapping("{kimlik}")
	public FireEmployeeResponse fireEmployee(@PathVariable String kimlik) {
		return hrService.fireEmployee(kimlik);
	}
}
