package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "department")
public class Department {
	
	@Id
	@Column(name = "department_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(value = "Unique id for department", required = true, example = "1")
	private int departmentId;
	
	@Column(name = "department_name")
	@Size(min = 2, message = "Department name should be more than 2 characters")
	@Pattern(regexp = "^[A-Za-z-]+$", message = "Department name should have only Alphabets and hyphen")
	@ApiModelProperty(value = "Name of the department", required = true, example = "IT")
	private String departmentName;
	
	public Department(int departmentId){
		super();
		this.departmentId = departmentId;
	}

}
