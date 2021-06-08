package com.example.hr.adapter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.repository.EmployeeDocumentRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository {
	@Autowired
	private EmployeeDocumentRepository empRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean exists(TcKimlikNo tcKimlikNo) {
		return empRepo.existsById(tcKimlikNo.getValue());
	}

	@Override
	public void save(Employee employee) {
		empRepo.insert(modelMapper.map(employee, EmployeeDocument.class));
	}

	@Override
	public Employee findByIdentity(TcKimlikNo kimlik) {
		var employeeDocument = empRepo.findById(kimlik.getValue());
		if (employeeDocument.isEmpty())
			throw new IllegalArgumentException("Cannot find employee");
		return modelMapper.map(employeeDocument.get(), Employee.class);
	}

	@Override
	public void remove(Employee employee) {
		empRepo.deleteById(employee.getTcKimlikNo().getValue());
	}

}
