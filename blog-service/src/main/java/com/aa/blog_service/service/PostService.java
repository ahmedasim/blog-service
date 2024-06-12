package com.aa.blog_service.service;

import java.util.List;

import com.aa.blog_service.dto.PostRequestDto;
import com.aa.blog_service.dto.PostResponseDto;
import com.aa.blog_service.entity.Post;

public interface PostService{
	PostResponseDto savePost(PostRequestDto dto);

	PostResponseDto updatePost(PostRequestDto dto, Long postId);
	
	void deletePost(Long postId);

	PostResponseDto getPostById(Long postId);
	
	List<PostResponseDto> getPosts();
	
	List<PostResponseDto> getPostsByAuthorId(Long authorId);
	
	List<PostResponseDto> getAuthorPostsByAuthorIdAndDeleted(Long authorId, boolean isDeleted);
	
	Post findById(Long postId);
	
	List<PostResponseDto> getPostsByPagination(Integer page, Integer size);	

}
