package com.example.demo.controller;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.api.ApiResponseModel;
import com.example.demo.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;


@RestController
@Api(tags = "Employee")
public class EmployeeController {
	
	static final Logger logger  = LogManager.getLogger(EmployeeController.class.getName());
	
	private static final String NUMBER_PATTERN = "^[0-9]+$";
	private static final String NUMBER_ERROR_MESSAGE = "Id can be number only";
	private static final int BAD_REQUEST = 400;

	@Autowired
	private EmployeeService employeeService;
	
	@ApiOperation(value = "Retrieve all employee details",
	        produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value="/employees", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployee(){
		return employeeService.getAllEmployees();
	}

	@ApiOperation(value = "Retrieve employee by id", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = BAD_REQUEST, message = "Invalid Request",
            responseHeaders = @ResponseHeader(name = "application/error+json", description = "Explains it is an error",
                    response = Boolean.class))})
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping(value="/employees/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployee(@NotNull @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_ERROR_MESSAGE) @PathVariable int id){
		return employeeService.getEmployee(id);
	}
	
	@ApiOperation(value = "Add employee detail", httpMethod = "POST")
    @ApiResponses(value = { @ApiResponse(code = BAD_REQUEST, message = "Invalid Request",
            responseHeaders = @ResponseHeader(name = "application/error+json", description = "Explains it is an error",
                    response = Boolean.class))})
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping(value="/employees", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseModel addEmployees(@Validated @Valid @RequestBody Employee employee){
		return employeeService.addEmployee(employee);
	}

	@ApiOperation(value = "Update employee detail", httpMethod = "PUT")
    @ApiResponses(value = { @ApiResponse(code = BAD_REQUEST, message = "Invalid Request",
            responseHeaders = @ResponseHeader(name = "application/error+json", description = "Explains it is an error",
                    response = Boolean.class))})
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping("/employees/{id}")
	public ApiResponseModel updateEmployee(@RequestBody Employee e, @NotNull @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_ERROR_MESSAGE) @PathVariable int id){
		return employeeService.updateEmployee(e, id);
	}
	
	@ApiOperation(value = "Delete all employee detail", httpMethod = "DELETE")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/employees")
	public ApiResponseModel deleteAllEmployees(){
		return employeeService.deleteAllEmployees();
	}

	@ApiOperation(value = "Delete an employee detail", httpMethod = "DELETE")
    @ApiResponses(value = { @ApiResponse(code = BAD_REQUEST, message = "Invalid Request",
            responseHeaders = @ResponseHeader(name = "application/error+json", description = "Explains it is an error",
                    response = Boolean.class))})
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/employees/{id}")
	public ApiResponseModel deleteEmployeeByID(@RequestBody Employee e, @NotNull @Pattern(regexp = NUMBER_PATTERN, message = NUMBER_ERROR_MESSAGE) @PathVariable int id){
		return employeeService.deleteEmployeeByID(id);
	}

	@ApiOperation(value = "Update employee detail", httpMethod = "PATCH")
    @ApiResponses(value = { @ApiResponse(code = BAD_REQUEST, message = "Invalid Request",
            responseHeaders = @ResponseHeader(name = "application/error+json", description = "Explains it is an error",
                    response = Boolean.class))})
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PatchMapping("/employees/{id}")
	public ApiResponseModel patchEmployeeByID(@RequestBody Employee e, @NotNull @Pattern(regexp = NUMBER_PATTERN) @PathVariable int id) {
		return employeeService.patchEmployee(e, id);
	}
}
