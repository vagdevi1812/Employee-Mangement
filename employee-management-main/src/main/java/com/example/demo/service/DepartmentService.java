package com.example.demo.service;

import static com.example.demo.utils.ApiResponseBuilder.buildApiResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.model.Department;
import com.example.demo.model.api.ApiResponseModel;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	

	public List<Department> getAllDepartments(){
		List<Department> depts = (List<Department>)departmentRepository.findAll(); 
		return depts;
	}
	
	public Department getDepartment(int id){
		return departmentRepository.findById(id).get();
	}
	
	public ApiResponseModel addDepartment(Department d) {
		departmentRepository.save(d);
		return buildApiResponse(HttpStatus.CREATED.value(), "Record is created successfully.");
	}
	
	public ApiResponseModel updateDepartment(Department d, int id){
		if(id == d.getDepartmentId()) {
			departmentRepository.save(d);
		}
		return buildApiResponse(HttpStatus.OK.value(), "Record is updated successfully.");
	}
	
	public ApiResponseModel deleteAllDepartment(){
		departmentRepository.deleteAll();
		return buildApiResponse(HttpStatus.OK.value(), "All record are deleted successfully.");
	}
	
	public ApiResponseModel deleteDepartmentByID(int id){
		departmentRepository.deleteById(id);
		return buildApiResponse(HttpStatus.OK.value(), "Record is deleted successfully.");
	}
	
	
	public ApiResponseModel patchDepartment(Department d, int id) {
		if(id == d.getDepartmentId()) {
			departmentRepository.save(d);
		}
		return buildApiResponse(HttpStatus.OK.value(), "Record is updated successfully.");
	}
}