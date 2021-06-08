package com.example.hr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.FiatCurrency;

// http://binkurt.blogspot.com/2015/02/mongodb-ile-calsmak.html
@Repository
public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument, String> {
	List<EmployeeDocument> findByBirthYearBetween(int fromYear, int toYear);
	
	List<EmployeeDocument> findByBirthYearBetweenAndSalaryGreaterThan(int fromYear, int toYear,double salaryLimit);

	List<EmployeeDocument> findByCurrency(FiatCurrency currency);
	
	@Query("{$and : [ { birthYear: {$gte: ?0} }, { birthYear: {$lt: ?1}}, {salary: {$gte: ?2}} ]")
	List<EmployeeDocument> yilAraliginaGoreCalisanlariGetir(int fromYear, int toYear,double salaryLimit);	

}
