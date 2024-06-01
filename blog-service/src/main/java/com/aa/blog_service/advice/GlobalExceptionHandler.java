package com.aa.blog_service.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aa.blog_service.dto.common.ApiError;
import com.aa.blog_service.dto.common.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGlobalException(Exception ex) { 
        ApiResponse<?> apiResponse = new ApiResponse<>();
        List<ApiError> errors = new ArrayList<>();
        ApiError error = new ApiError();
        error.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR + "");
        error.setCustomErrorCode("");
        error.setMessage(ex.getMessage());
        errors.add(error);
        apiResponse.setApiErrors(errors);
        return apiResponse;
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) { 
        ApiResponse<?> apiResponse = new ApiResponse<>();
        List<ApiError> errors = new ArrayList<>();
        
        BindingResult bindingResult = ex.getBindingResult();
        bindingResult.getFieldErrors().forEach(fieldError ->
                errors.add(new ApiError(HttpStatus.BAD_REQUEST + "", fieldError.getField(), fieldError.getDefaultMessage()))
        );
        
        apiResponse.setApiErrors(errors);
        return apiResponse;
    }
	
	
}
