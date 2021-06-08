package com.example.hr.application.business;

import java.util.Objects;
import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.event.EmployeeEvent;
import com.example.hr.event.EmployeeFiredEvent;
import com.example.hr.event.EmployeeHiredEvent;
import com.example.hr.infrastructure.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication /* API */{
	private EmployeeRepository employeeRepo; // depends on SPI (Service Provider Interface)
	private EventPublisher<EmployeeEvent> publisher; // depends on SPI
	
	// Dependency Injection through constructor
	public StandardHrApplication(EmployeeRepository employeeRepo, EventPublisher<EmployeeEvent> publisher) {
		this.employeeRepo = employeeRepo;
		this.publisher = publisher;
	}

	@Override
	public boolean hireEmployee(Employee employee) {
		var tcKimlikNo = employee.getTcKimlikNo();
		if (employeeRepo.exists(tcKimlikNo ))
			return false;
		employeeRepo.save(employee);
		var event = new EmployeeHiredEvent(tcKimlikNo);
		publisher.publish(event);
		return true;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo kimlik) {
		var employee = employeeRepo.findByIdentity(kimlik);
		if (Objects.isNull(employee))
			return Optional.empty();
		employeeRepo.remove(employee);
		var event = new EmployeeFiredEvent(kimlik);		
		publisher.publish(event);
		return Optional.of(employee);
	}

}
