package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
	
	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "Unique id for employee", required = true, example = "1")
	private int employeeId;
	
	@Column(name = "first_name")
	@NotNull
	@Size(min = 2, message = "First name should be more than 2 characters")
	@Pattern(regexp = "^[A-Za-z'-]+$", message = "First name should have only Alphabets, hypen and '")
	@ApiModelProperty(value = "First name of employee", required = true, example = "John")
	private String firstName;
	
	@Column(name = "last_name")
	@NotNull
	@Size(min = 2, message = "Last name should be more than 2 characters")
	@Pattern(regexp = "^[A-Za-z'-]+$", message = "Last name should have only Alphabets, hypen and '")
	@ApiModelProperty(value = "Last name of employee", required = true, example = "Smith")
	private String lastName;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "department_id")
	@ApiModelProperty(value = "Department id of the employee", required = true, example = "1")
	private Department department;
	
	public Employee(String firstName, String lastName, Department department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
	}

}
