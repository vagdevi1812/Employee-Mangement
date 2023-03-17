package com.example.demo.utils;

import com.example.demo.model.api.ApiResponseModel;

public class ApiResponseBuilder {
	
public static ApiResponseModel buildApiResponse(int code, String message) {
		
		return ApiResponseModel.builder()
						  .statusCode(code)
						  .message(message)
						  .build();
	}

}
