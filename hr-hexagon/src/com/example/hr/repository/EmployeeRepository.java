package com.example.hr.repository;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public interface EmployeeRepository {

	boolean exists(TcKimlikNo tcKimlikNo);

	void save(Employee employee);

	Employee findByIdentity(TcKimlikNo kimlik);

	void remove(Employee employee);

}
