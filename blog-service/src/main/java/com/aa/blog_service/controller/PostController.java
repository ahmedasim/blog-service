package com.aa.blog_service.controller;

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
	public ApiResponse<PostResponseDto> savePost(@Valid @RequestBody PostRequestDto requestDto) {
		ApiResponse<PostResponseDto> apiResponse = new ApiResponse<>();
		PostResponseDto responseDto = service.savePost(requestDto);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseDto);
		apiResponse.setMessage("Post saved successfully");
		return apiResponse;
	}
	
	@PutMapping("/{postId}")
	public ApiResponse<PostResponseDto> updatePost(@RequestBody PostRequestDto requestDto, @PathVariable Long postId) {
		ApiResponse<PostResponseDto> apiResponse = new ApiResponse<>();
		PostResponseDto responseDto = service.updatePost(requestDto, postId);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseDto);
		apiResponse.setMessage("Post updated successfully");
		return apiResponse;
	}
	
	@DeleteMapping("/{postId}")
	public ApiResponse<PostResponseDto> deletePost(@PathVariable Long postId) {
		ApiResponse<PostResponseDto> apiResponse = new ApiResponse<>();
		service.deletePost(postId);
		apiResponse.setSuccess(true);
		apiResponse.setMessage("Post deleted successfully");
		return apiResponse;
	}
	
	@GetMapping("/{postId}")
	public ApiResponse<PostResponseDto> getPostById(@PathVariable Long postId) {
		ApiResponse<PostResponseDto> apiResponse = new ApiResponse<>();
		PostResponseDto responseDto = service.getPostById(postId);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseDto);
		apiResponse.setMessage("Post fetched successfully");
		return apiResponse;
	}
	
	@GetMapping
	public ApiResponse<List<PostResponseDto>> findAll() {
		ApiResponse<List<PostResponseDto>> apiResponse = new ApiResponse<>();
		List<PostResponseDto> responseList = service.getPosts();
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseList);
		apiResponse.setMessage("Posts fetched successfully");
		return apiResponse;
	}
	
	@GetMapping("/author-posts/{authorId}")
	public ApiResponse<List<PostResponseDto>> getPostsByAuthorId(@PathVariable Long authorId) {
		ApiResponse<List<PostResponseDto>> apiResponse = new ApiResponse<>();
		List<PostResponseDto> responseList = service.getPostsByAuthorId(authorId);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseList);
		apiResponse.setMessage("Posts fetched successfully");
		return apiResponse;
	}
	
	@GetMapping("/author-deleted-posts/{authorId}")
	public ApiResponse<List<PostResponseDto>> getDeletedPostByAuthor(@PathVariable Long authorId) {
		ApiResponse<List<PostResponseDto>> apiResponse = new ApiResponse<>();
		List<PostResponseDto> responseList = service.getAuthorPostsByAuthorIdAndDeleted(authorId, true);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseList);
		apiResponse.setMessage("Posts fetched successfully");
		return apiResponse;
	}
	
	@GetMapping("/author-active-posts/{authorId}")
	public ApiResponse<List<PostResponseDto>> getActivePostByAuthor(@PathVariable Long authorId) {
		ApiResponse<List<PostResponseDto>> apiResponse = new ApiResponse<>();
		List<PostResponseDto> responseList = service.getAuthorPostsByAuthorIdAndDeleted(authorId, false);
		apiResponse.setSuccess(true);
		apiResponse.setResponse(responseList);
		apiResponse.setMessage("Posts fetched successfully");
		return apiResponse;
	}
	
}
