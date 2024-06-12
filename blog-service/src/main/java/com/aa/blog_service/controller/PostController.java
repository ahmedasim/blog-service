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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aa.blog_service.dto.PostRequestDto;
import com.aa.blog_service.dto.PostResponseDto;
import com.aa.blog_service.dto.common.ApiResponse;
import com.aa.blog_service.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

	@Autowired
	PostService service;
	
	@PostMapping
	public ResponseEntity<ApiResponse<PostResponseDto>> savePost(@Valid @RequestBody PostRequestDto requestDto) {
		ApiResponse<PostResponseDto> apiResponse = new ApiResponse<>();
		PostResponseDto responseDto = service.savePost(requestDto);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseDto);
		apiResponse.setMessage("Post saved successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<ApiResponse<PostResponseDto>> updatePost(@RequestBody PostRequestDto requestDto, @PathVariable Long postId) {
		ApiResponse<PostResponseDto> apiResponse = new ApiResponse<>();
		PostResponseDto responseDto = service.updatePost(requestDto, postId);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseDto);
		apiResponse.setMessage("Post updated successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/{postId}")
	//Sending Ok, cause contents are not actually deleted
	public ResponseEntity<ApiResponse<PostResponseDto>> deletePost(@PathVariable Long postId) {
		ApiResponse<PostResponseDto> apiResponse = new ApiResponse<>();
		service.deletePost(postId);
		apiResponse.setSuccess(true);
		apiResponse.setMessage("Post deleted successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<ApiResponse<PostResponseDto>> getPostById(@PathVariable Long postId) {
		ApiResponse<PostResponseDto> apiResponse = new ApiResponse<>();
		PostResponseDto responseDto = service.getPostById(postId);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseDto);
		apiResponse.setMessage("Post fetched successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<PostResponseDto>>> getPosts() {
		ApiResponse<List<PostResponseDto>> apiResponse = new ApiResponse<>();
		List<PostResponseDto> responseList = service.getPosts();
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseList);
		apiResponse.setMessage("Posts fetched successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/author-posts/{authorId}")
	public ResponseEntity<ApiResponse<List<PostResponseDto>>> getPostsByAuthorId(@PathVariable Long authorId) {
		ApiResponse<List<PostResponseDto>> apiResponse = new ApiResponse<>();
		List<PostResponseDto> responseList = service.getPostsByAuthorId(authorId);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseList);
		apiResponse.setMessage("Posts fetched successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/author-deleted-posts/{authorId}")
	public ResponseEntity<ApiResponse<List<PostResponseDto>>> getDeletedPostByAuthor(@PathVariable Long authorId) {
		ApiResponse<List<PostResponseDto>> apiResponse = new ApiResponse<>();
		List<PostResponseDto> responseList = service.getAuthorPostsByAuthorIdAndDeleted(authorId, true);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseList);
		apiResponse.setMessage("Posts fetched successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/author-active-posts/{authorId}")
	public ResponseEntity<ApiResponse<List<PostResponseDto>>> getActivePostByAuthor(@PathVariable Long authorId) {
		ApiResponse<List<PostResponseDto>> apiResponse = new ApiResponse<>();
		List<PostResponseDto> responseList = service.getAuthorPostsByAuthorIdAndDeleted(authorId, false);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseList);
		apiResponse.setMessage("Posts fetched successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/by-page")
	public ResponseEntity<ApiResponse<List<PostResponseDto>>> getPostsByPagination(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber, 
			@RequestParam(name="pageSize", defaultValue = "10") Integer pageSize) {
		ApiResponse<List<PostResponseDto>> apiResponse = new ApiResponse<>();
		List<PostResponseDto> responseList = service.getPostsByPagination(pageNumber, pageSize);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseList);
		apiResponse.setMessage("Posts fetched successfully");
		return ResponseEntity.ok(apiResponse);
	}
	
}
