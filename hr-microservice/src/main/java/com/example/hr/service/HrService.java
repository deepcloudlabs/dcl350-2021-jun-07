package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hr.application.HrApplication;
import com.example.hr.boundary.FireEmployeeResponse;
import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.boundary.HireEmployeeResponse;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Service
public class HrService {
	@Autowired
	private HrApplication hrApplication; // delegates to the implementation in the hexagon
	@Autowired // ACL (Anti-Corruption Layer)/OHS (Open Host Service)
	private ModelMapper modelMapper;
	
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		var success = hrApplication.hireEmployee(employee);
		if (success)
			return new HireEmployeeResponse("success");
		return new HireEmployeeResponse("failure");
	}

	public FireEmployeeResponse fireEmployee(String kimlik) {
		var firedEmployee = hrApplication.fireEmployee(TcKimlikNo.of(kimlik));
		if (firedEmployee.isEmpty())
			return new FireEmployeeResponse("failure");
		return modelMapper.map(firedEmployee.get(), FireEmployeeResponse.class);
	}

}
