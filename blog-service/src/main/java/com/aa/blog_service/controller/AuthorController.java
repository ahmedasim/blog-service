package com.aa.blog_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.aa.blog_service.dto.common.ApiResponse;
import com.aa.blog_service.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

	@Autowired
	AuthorService service;
	
	@PostMapping
	public ResponseEntity<ApiResponse<AuthorResponseDto>> saveAuthor(@Valid @RequestBody AuthorRequestDto requestDto) {
		ApiResponse<AuthorResponseDto> apiResponse = new ApiResponse<>();
		AuthorResponseDto responseDto = service.saveAuthor(requestDto);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseDto);
		apiResponse.setMessage("Author saved successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}
	
	@PutMapping("/{authorId}")
	public ResponseEntity<ApiResponse<AuthorResponseDto>> updateAuthor(@Valid @RequestBody AuthorRequestDto requestDto, @PathVariable Long authorId) {
		ApiResponse<AuthorResponseDto> apiResponse = new ApiResponse<>();
		AuthorResponseDto responseDto = service.updateAuthor(requestDto, authorId);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseDto);
		apiResponse.setMessage("Author updated successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	//Sending ok response instead of no content cause wants api response in response body
	@DeleteMapping("/{authorId}")
	public ResponseEntity<ApiResponse<AuthorResponseDto>> deleteAuthor(@PathVariable Long authorId) {
		ApiResponse<AuthorResponseDto> apiResponse = new ApiResponse<>();
		service.deleteAuthor(authorId);
		apiResponse.setSuccess(true);
		apiResponse.setMessage("Author deleted successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{authorId}")
	public ResponseEntity<ApiResponse<AuthorResponseDto>> getAuthorById(@PathVariable Long authorId) {
		ApiResponse<AuthorResponseDto> apiResponse = new ApiResponse<>();
		AuthorResponseDto responseDto = service.getAuthorById(authorId);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseDto);
		apiResponse.setMessage("Author fetched successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<AuthorResponseDto>>> getAuthors() {
		ApiResponse<List<AuthorResponseDto>> apiResponse = new ApiResponse<>();
		List<AuthorResponseDto> responseList = service.getAuthors();
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseList);
		apiResponse.setMessage("Authors fetched successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
}
