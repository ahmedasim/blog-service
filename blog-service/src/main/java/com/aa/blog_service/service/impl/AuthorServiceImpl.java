package com.aa.blog_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aa.blog_service.dto.AuthorRequestDto;
import com.aa.blog_service.dto.AuthorResponseDto;
import com.aa.blog_service.entity.Author;
import com.aa.blog_service.exception.ResourceNotFoundException;
import com.aa.blog_service.repo.AuthorRepo;
import com.aa.blog_service.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	AuthorRepo repo;
	
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public AuthorResponseDto saveAuthor(AuthorRequestDto requestDto) {
		Author author = objectMapper.convertValue(requestDto, Author.class);
		repo.save(author);
		return objectMapper.convertValue(author, AuthorResponseDto.class);
	}

	@Override
	public AuthorResponseDto updateAuthor(AuthorRequestDto requestDto, Long authorId) {
		Author author = findById(authorId);
		author.setName(requestDto.getName());
		repo.save(author);
		return objectMapper.convertValue(author, AuthorResponseDto.class);
	}

	@Override
	public void deleteAuthor(Long authorId) {
		Author author = findById(authorId);
		repo.delete(author);
	}

	@Override
	public AuthorResponseDto getAuthorById(Long authorId) {
		Author author = findById(authorId);
		return objectMapper.convertValue(author, AuthorResponseDto.class);
	}

	@Override
	public List<AuthorResponseDto> getAuthors() {
		return repo.findAll().stream().map(user -> objectMapper.convertValue(user, AuthorResponseDto.class)).toList();
	}

	@Override
	public Author findById(Long authorId) {
		return repo.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("Author not found!"));
	}
}
