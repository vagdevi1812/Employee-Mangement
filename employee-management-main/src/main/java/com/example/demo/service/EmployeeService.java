package com.example.demo.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.api.ApiResponseModel;
import com.example.demo.repository.EmployeeRepository;

import static com.example.demo.utils.ApiResponseBuilder.buildApiResponse;

// employee service class
@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees(){
		List<Employee> emps = (List<Employee>)employeeRepository.findAll(); 
		return emps;
	}
	
	public Employee getEmployee(int id){
		return employeeRepository.findById(id).get();
	}
	
	@Transactional
	public ApiResponseModel addEmployee(Employee e) {
		employeeRepository.save(e);
		return buildApiResponse(HttpStatus.CREATED.value(), "Record is created successfully.");
	}
	
	

	@Transactional
	public ApiResponseModel updateEmployee(Employee emp, int id){
		if(id == emp.getEmployeeId()) {
			employeeRepository.save(emp);
		}
		return buildApiResponse(HttpStatus.OK.value(), "Record is updated successfully.");
	}
	
	public ApiResponseModel deleteAllEmployees(){
		employeeRepository.deleteAll();
		return buildApiResponse(HttpStatus.OK.value(), "All record are deleted successfully.");
	}
	
	public ApiResponseModel deleteEmployeeByID(int id){
		employeeRepository.deleteById(id);
		return buildApiResponse(HttpStatus.OK.value(), "Record is deleted successfully.");
	}
	
	@Transactional
	public ApiResponseModel patchEmployee(Employee emp, int id) {
		if(id == emp.getEmployeeId()) {
			employeeRepository.save(emp);
		}
		return buildApiResponse(HttpStatus.OK.value(), "Record is updated successfully.");
	}
}