package com.example.hr.config;

import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.boundary.FireEmployeeResponse;
import com.example.hr.boundary.HireEmployeeRequest;
import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;

@Configuration
public class ModelMapperConfig {
	private static final Converter<HireEmployeeRequest, Employee>
	    HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER = context -> {
	    	var request = context.getSource();
	    	System.err.println(request);
	    	return new Employee.Builder(request.getIdentity())
	    			                   .fullname(request.getFirstName(), request.getLastName())
	    			                   .salary(request.getSalary(), request.getCurrency())
	    			                   .birthYear(request.getBirthYear())
	    			                   .style(request.getStyle())
	    			                   .departments(request.getDepartments().toArray(new String[0]))
	    			                   .iban(request.getIban())
	    			                   .photo(request.getPhoto())
	    			                   .build();
	    };
	    private static final Converter<EmployeeDocument, Employee>
	    EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER = context -> {
	    	var document = context.getSource();
	    	return new Employee.Builder(document.getIdentity())
	    			.fullname(document.getFirstName(), document.getLastName())
	    			.salary(document.getSalary(), document.getCurrency())
	    			.birthYear(document.getBirthYear())
	    			.style(document.getStyle())
	    			.departments(document.getDepartments().toArray(new String[0]))
	    			.iban(document.getIban())
	    			.photo(document.getPhoto())
	    			.build();
	    };
	    private static final Converter<Employee, EmployeeDocument>
	    EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER = context -> {
	    	var employee = context.getSource();
	    	var document = new EmployeeDocument();
	    	document.setIdentity(employee.getTcKimlikNo().getValue());
	    	document.setFirstName(employee.getFullname().getFirstName());
	    	document.setLastName(employee.getFullname().getLastName());
	    	document.setBirthYear(employee.getBirthYear().getValue());
	    	document.setPhoto(employee.getPhoto().getBase64Value());
	    	document.setDepartments(employee.getDepartments().stream().map(Department::name).collect(Collectors.toSet()));
	    	document.setCurrency(employee.getSalary().getCurrency());
	    	document.setSalary(employee.getSalary().getValue());
	    	document.setIban(employee.getIban().getValue());
	    	document.setStyle(employee.getStyle());
	    	return document;
	    };
	    private static final Converter<Employee, FireEmployeeResponse>
	    EMPLOYEE_TO_FIRE_EMPLOYEE_RESPONSE_CONVERTER = context -> {
	    	var employee = context.getSource();
	    	var response = new FireEmployeeResponse();
	    	response.setIdentity(employee.getTcKimlikNo().getValue());
	    	response.setFirstName(employee.getFullname().getFirstName());
	    	response.setLastName(employee.getFullname().getLastName());
	    	return response;
	    };
	
	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, 
				     HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_FIRE_EMPLOYEE_RESPONSE_CONVERTER, 
				Employee.class, FireEmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER, 
				Employee.class, EmployeeDocument.class);
		modelMapper.addConverter(EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER, 
				EmployeeDocument.class, Employee.class);
		return modelMapper;
	}
}
