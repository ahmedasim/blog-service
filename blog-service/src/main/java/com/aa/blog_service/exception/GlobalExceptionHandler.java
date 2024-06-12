package com.aa.blog_service.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aa.blog_service.dto.common.ApiError;
import com.aa.blog_service.dto.common.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleGlobalException(ResourceNotFoundException ex) { 
		ApiResponse<?> apiResponse = new ApiResponse<>();
        List<ApiError> errors = new ArrayList<>();
        ApiError error = new ApiError();
        error.setErrorCode("RESOURCE_NOT_FOUND");
        error.setMessage(ex.getMessage());
        errors.add(error);
        apiResponse.setApiErrors(errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGlobalException(Exception ex) { 
        ApiResponse<?> apiResponse = new ApiResponse<>();
        List<ApiError> errors = new ArrayList<>();
        ApiError error = new ApiError();
        error.setErrorCode("SERVER_ERROR");
        error.setMessage(ex.getMessage());
        errors.add(error);
        apiResponse.setApiErrors(errors);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) { 
        ApiResponse<?> apiResponse = new ApiResponse<>();
        List<ApiError> errors = new ArrayList<>();
        
        BindingResult bindingResult = ex.getBindingResult();
        bindingResult.getFieldErrors().forEach(fieldError ->
                errors.add(new ApiError("BAD_REQUEST", fieldError.getField(), fieldError.getDefaultMessage()))
        );
        apiResponse.setApiErrors(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
	
	
}
