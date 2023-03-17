package com.example.demo.controller;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Department;
import com.example.demo.model.api.ApiResponseModel;
import com.example.demo.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;



@RestController
@Api(tags = "Department")
public class DepartmentController {
	
	static final Logger logger  = LogManager.getLogger(DepartmentController.class.getName());
	
	private static final String NUMBER_PATTERN = "^[0-9]+$";
	private static final int BAD_REQUEST = 400;
	
	@Autowired
	private DepartmentService departmentService;
	
		@ApiOperation(value = "Retrieve all department details",
	        produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
		@PreAuthorize("hasRole('ADMIN')")
		@GetMapping(value="/departments", produces=MediaType.APPLICATION_JSON_VALUE)
		public List<Department> getAllDepartment(){
			return departmentService.getAllDepartments();
		}

		@ApiOperation(value = "Retrieve department by id", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	    @ApiResponses(value = { @ApiResponse(code = BAD_REQUEST, message = "Invalid Request",
	            responseHeaders = @ResponseHeader(name = "application/error+json", description = "Explains it is an error",
	                    response = Boolean.class))})
		@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
		@GetMapping(value="/departments/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
		public Department getDepartment(@NotNull @Pattern(regexp = NUMBER_PATTERN) @PathVariable int id){
			return departmentService.getDepartment(id);
		}
		
		@ApiOperation(value = "Add department detail", httpMethod = "POST")
	    @ApiResponses(value = { @ApiResponse(code = BAD_REQUEST, message = "Invalid Request",
	            responseHeaders = @ResponseHeader(name = "application/error+json", description = "Explains it is an error",
	                    response = Boolean.class))})
		@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
		@PostMapping("/departments")
		public ApiResponseModel addDepartment(@RequestBody Department department){
			return departmentService.addDepartment(department);
		}

		@ApiOperation(value = "Update department detail  by ID", httpMethod = "PUT")
	    @ApiResponses(value = { @ApiResponse(code = BAD_REQUEST, message = "Invalid Request",
	            responseHeaders = @ResponseHeader(name = "application/error+json", description = "Explains it is an error",
	                    response = Boolean.class))})
		@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
		@PutMapping("/departments/{id}")
		public ApiResponseModel updateDepartment(@RequestBody Department d, @NotNull @Pattern(regexp = NUMBER_PATTERN) @PathVariable int id){
			return departmentService.updateDepartment(d, id);
		}
		
		@ApiOperation(value = "Delete all department detail", httpMethod = "DELETE")
		@PreAuthorize("hasRole('ADMIN')")
		@DeleteMapping("/departments")
		public ApiResponseModel deleteAllDepartments(){
			return departmentService.deleteAllDepartment();
		}

		@ApiOperation(value = "Delete a department detail", httpMethod = "DELETE")
	    @ApiResponses(value = { @ApiResponse(code = BAD_REQUEST, message = "Invalid Request",
	            responseHeaders = @ResponseHeader(name = "application/error+json", description = "Explains it is an error",
	                    response = Boolean.class))})
		@PreAuthorize("hasRole('ADMIN')")
		@DeleteMapping("/departments/{id}")
		public ApiResponseModel deleteDepartmentByID(@RequestBody Department d, @NotNull @Pattern(regexp = NUMBER_PATTERN) @PathVariable int id){
			return departmentService.deleteDepartmentByID(id);
		}

		@ApiOperation(value = "Update department detail", httpMethod = "PATCH")
	    @ApiResponses(value = { @ApiResponse(code = BAD_REQUEST, message = "Invalid Request",
	            responseHeaders = @ResponseHeader(name = "application/error+json", description = "Explains it is an error",
	                    response = Boolean.class))})		@PatchMapping("/departments/{id}")
		@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
		public ApiResponseModel patchDepartmentByID(@RequestBody Department d, @NotNull @Pattern(regexp = NUMBER_PATTERN) @PathVariable int id) {
			return departmentService.patchDepartment(d, id);
		}
}