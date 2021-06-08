package com.example.hr.application;

import java.util.Optional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

// Single Business Capability
// Interface Segregation Principle
public interface HrApplication {
	boolean hireEmployee(Employee employee);
	Optional<Employee> fireEmployee(TcKimlikNo kimlik);
}
