package com.aa.blog_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aa.blog_service.dto.AuthorRequestDto;
import com.aa.blog_service.dto.AuthorResponseDto;
import com.aa.blog_service.dto.common.ApiError;
import com.aa.blog_service.dto.common.ApiResponse;
import com.aa.blog_service.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

	@Autowired
	AuthorService service;
	
	@PostMapping
	public ApiResponse<AuthorResponseDto> saveAuthor(@Valid @RequestBody AuthorRequestDto requestDto) {
		ApiResponse<AuthorResponseDto> apiResponse = new ApiResponse<>();
		try {
			AuthorResponseDto responseDto = service.saveAuthor(requestDto);
			apiResponse.setSuccess(true);
			apiResponse.setResponse(responseDto);
			apiResponse.setMessage("Author saved successfully");
			return apiResponse;
		}catch(Exception e) {
			List<ApiError> errors = new ArrayList<ApiError>();
			errors.add(new ApiError("", "", e.getMessage()));
			apiResponse.setApiErrors(errors );
		}
		return apiResponse;
	}
	
	@PutMapping("/{authorId}")
	public ApiResponse<AuthorResponseDto> updateAuthor(@Valid @RequestBody AuthorRequestDto requestDto, @PathVariable Long authorId) {
		ApiResponse<AuthorResponseDto> apiResponse = new ApiResponse<>();
		try {
			AuthorResponseDto responseDto = service.updateAuthor(requestDto, authorId);
			apiResponse.setSuccess(true);
			apiResponse.setResponse(responseDto);
			apiResponse.setMessage("Author updated successfully");
			return apiResponse;
		}catch(Exception e) {
			List<ApiError> errors = new ArrayList<ApiError>();
			errors.add(new ApiError("", "", e.getMessage()));
			apiResponse.setApiErrors(errors );
		}
		return apiResponse;
	}
	
	@DeleteMapping("/{authorId}")
	public ApiResponse<AuthorResponseDto> deleteAuthor(@PathVariable Long authorId) {
		ApiResponse<AuthorResponseDto> apiResponse = new ApiResponse<>();
		try {
			service.deleteAuthor(authorId);
			apiResponse.setSuccess(true);
			apiResponse.setMessage("Author deleted successfully");
			return apiResponse;
		}catch(Exception e) {
			List<ApiError> errors = new ArrayList<ApiError>();
			errors.add(new ApiError("", "", e.getMessage()));
			apiResponse.setApiErrors(errors );
		}
		return apiResponse;
	}
	
	@GetMapping("/{authorId}")
	public ApiResponse<AuthorResponseDto> getAuthorById(@PathVariable Long authorId) {
		ApiResponse<AuthorResponseDto> apiResponse = new ApiResponse<>();
		try {
			AuthorResponseDto responseDto = service.getAuthorById(authorId);
			apiResponse.setSuccess(true);
			apiResponse.setResponse(responseDto);
			apiResponse.setMessage("Author fetched successfully");
			return apiResponse;
		}catch(Exception e) {
			List<ApiError> errors = new ArrayList<ApiError>();
			errors.add(new ApiError("", "", e.getMessage()));
			apiResponse.setApiErrors(errors );
		}
		return apiResponse;
	}
	
	@GetMapping
	public ApiResponse<List<AuthorResponseDto>> getAuthors() {
		ApiResponse<List<AuthorResponseDto>> apiResponse = new ApiResponse<>();
		try {
			List<AuthorResponseDto> responseList = service.getAuthors();
			apiResponse.setSuccess(true);
			apiResponse.setResponse(responseList);
			apiResponse.setMessage("Authors fetched successfully");
			return apiResponse;
		}catch(Exception e) {
			List<ApiError> errors = new ArrayList<ApiError>();
			errors.add(new ApiError("", "", e.getMessage()));
			apiResponse.setApiErrors(errors );
		}
		return apiResponse;
	}
	
}
