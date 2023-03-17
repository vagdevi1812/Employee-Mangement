package com.example.demo.model.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Defines the JSON output format of error responses
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonPropertyOrder({ "errorCode", "errorMessage", "errorDescription" })
public class ErrorResponse {

    @ApiModelProperty(value = "Error Message", example = "EMPLOYEE_DOES_NOT_EXIST", required = true)
    private String errorMessage;
    @ApiModelProperty(value = "Each error can have an error code", example = "DE001", required = true)
    private String errorCode;
    @ApiModelProperty(value = "More detailed description of the error",
                      example = "Exception Occured while mapping the json fields", required = true)
    private String errorDescription;

}
