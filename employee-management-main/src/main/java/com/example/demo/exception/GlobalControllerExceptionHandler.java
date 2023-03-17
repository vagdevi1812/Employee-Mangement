package com.example.demo.exception;

import static com.example.demo.exception.ErrorCodes.BAD_REQUEST;
import static com.example.demo.exception.ErrorCodes.INVALID_TYPE;
import static com.example.demo.exception.ErrorCodes.REQUESTED_METHOD_NOT_ALLOWED;
import static com.example.demo.exception.ErrorCodes.SYSTEM_EXCEPTION;
import static com.example.demo.exception.ErrorCodes.MEDIA_TYPE_NOT_SUPPORTED;
import static com.example.demo.exception.ErrorCodes.ACCESS_DENIED;
import static java.util.stream.Collectors.joining;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.configuration.Translator;
import com.example.demo.model.api.ErrorResponse;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.Markers;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleServerBindingException(final ServletRequestBindingException srbe) {
        log.error("In ServletRequestBindingException exception handler");
        return ResponseEntity.status(BAD_REQUEST.getHttpStatus())
                             .body(buildErrorResponse(BAD_REQUEST, srbe.getMessage()));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleHttpMethodNotSupportedException(
                                         final HttpRequestMethodNotSupportedException hrmnse) {
        log.error("In HttpRequestMethodNotSupported exception handler:{}", hrmnse.getMessage());
        return ResponseEntity.status(REQUESTED_METHOD_NOT_ALLOWED.getHttpStatus())
                .body(buildErrorResponse(REQUESTED_METHOD_NOT_ALLOWED, REQUESTED_METHOD_NOT_ALLOWED.getErrorDescription()));
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleBadRequestException(final MethodArgumentNotValidException manve) {
        String errorMessage = manve.getBindingResult()
                                   .getAllErrors()
                                   .stream()
                                   .peek(error -> log.error(error.getDefaultMessage()))
                                   .map(ObjectError::getDefaultMessage)
                                   .collect(joining(", "));
        return ResponseEntity.status(BAD_REQUEST.getHttpStatus())
                             .body(buildErrorResponse(BAD_REQUEST, errorMessage));
    }
    
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
                                         final MethodArgumentTypeMismatchException matme) {
        log.error("In MethodArgumentTypeMismatchException exception handler:{}", matme.getMessage());
        return ResponseEntity.status(INVALID_TYPE.getHttpStatus())
                .body(buildErrorResponse(INVALID_TYPE, INVALID_TYPE.getErrorDescription()));
    }
    
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleMediaTypeNotSupportedException(final HttpMediaTypeNotSupportedException e) {
        log.error("In HttpMediaTypeNotSupported exception handler:{}", e.getMessage());
        return ResponseEntity.status(MEDIA_TYPE_NOT_SUPPORTED.getHttpStatus())
                .body(buildErrorResponse(MEDIA_TYPE_NOT_SUPPORTED, MEDIA_TYPE_NOT_SUPPORTED.getErrorDescription()));
    }
    
    @ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleMediaTypeNotAcceptableException(final HttpMediaTypeNotAcceptableException e) {
        log.error("In HttpMediaTypeNotAcceptableException exception handler:{}", e.getMessage());
        return ResponseEntity.status(MEDIA_TYPE_NOT_SUPPORTED.getHttpStatus())
                .body(buildErrorResponse(MEDIA_TYPE_NOT_SUPPORTED, MEDIA_TYPE_NOT_SUPPORTED.getErrorDescription()));
    }
    
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(final AccessDeniedException e) {
        log.error("In AccessDeniedException exception handler:{}", e.getMessage());
        return ResponseEntity.status(ACCESS_DENIED.getHttpStatus())
                .body(buildErrorResponse(ACCESS_DENIED, ACCESS_DENIED.getErrorDescription()));
    }
    

    private ErrorResponse buildErrorResponse(final ErrorCodes errorCodes, final String errorDescription) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                   .errorCode(errorCodes.getErrorCode())
                                                   .errorMessage(errorCodes.name())
                                                   .errorDescription(Translator.toLocale(errorDescription))
                                                   .build();
        log.error(Markers.append("exception", errorResponse), null);
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleOtherExceptions(final Exception e) {
        log.error("In handle All Exception:{}", e.getMessage(), e);
        return ResponseEntity.status(SYSTEM_EXCEPTION.getHttpStatus())
                             .body(ErrorResponse.builder()
                                                .errorCode(SYSTEM_EXCEPTION.getErrorCode())
                                                .errorMessage(SYSTEM_EXCEPTION.name())
                                                .errorDescription(SYSTEM_EXCEPTION.getErrorDescription())
                                                .build());
    }

}
