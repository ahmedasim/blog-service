package com.aa.blog_service.service;

import java.util.List;

import com.aa.blog_service.dto.AuthorRequestDto;
import com.aa.blog_service.dto.AuthorResponseDto;
import com.aa.blog_service.entity.Author;

public interface AuthorService{
	AuthorResponseDto saveAuthor(AuthorRequestDto requestDto);

	AuthorResponseDto updateAuthor(AuthorRequestDto requestDto, Long authorId);
	
	void deleteAuthor(Long authorId);

	AuthorResponseDto getAuthorById(Long authorId);
	
	List<AuthorResponseDto> getAuthors();

	Author findById(Long authorId);
	
}
