package com.example.demo.model.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseModel {
	
	@ApiModelProperty(value = "Returns the http status code", example = "200")
	private int statusCode;
	@ApiModelProperty(value = "Message for the operation performed", example = "Record is created successfully.")
	private String message;

}
