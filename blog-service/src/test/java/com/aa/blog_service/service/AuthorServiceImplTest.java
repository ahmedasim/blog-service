package com.aa.blog_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aa.blog_service.dto.AuthorRequestDto;
import com.aa.blog_service.dto.AuthorResponseDto;
import com.aa.blog_service.entity.Author;
import com.aa.blog_service.repo.AuthorRepo;
import com.aa.blog_service.service.impl.AuthorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @Mock
    private AuthorRepo repo;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;
    private AuthorRequestDto requestDto;
    private AuthorResponseDto responseDto;

    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setAuthorId(1L);
        author.setName("Asim Ahmed");

        requestDto = new AuthorRequestDto();
        requestDto.setName("Asim Ahmed");

        responseDto = new AuthorResponseDto();
        responseDto.setAuthorId(1L);
        responseDto.setName("Asim Ahmed");
    }

    @Test
    public void testSaveAuthor() {
        when(objectMapper.convertValue(requestDto, Author.class)).thenReturn(author);
        when(repo.save(author)).thenReturn(author);
        when(objectMapper.convertValue(author, AuthorResponseDto.class)).thenReturn(responseDto);

        AuthorResponseDto result = authorService.saveAuthor(requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    public void testUpdateAuthor() {
        when(repo.findById(1L)).thenReturn(Optional.of(author));
        when(objectMapper.convertValue(author, AuthorResponseDto.class)).thenReturn(responseDto);

        AuthorRequestDto updatedRequestDto = new AuthorRequestDto();
        updatedRequestDto.setName("Jane Doe");

        authorService.updateAuthor(updatedRequestDto, 1L);

        verify(repo).save(author);
    }

    @Test
    public void testDeleteAuthor() {
        when(repo.findById(1L)).thenReturn(Optional.of(author));

        authorService.deleteAuthor(1L);

        verify(repo).delete(author);
    }

    @Test
    public void testGetAuthorById() {
        when(repo.findById(1L)).thenReturn(Optional.of(author));
        when(objectMapper.convertValue(author, AuthorResponseDto.class)).thenReturn(responseDto);

        AuthorResponseDto result = authorService.getAuthorById(1L);

        assertEquals(responseDto, result);
    }

    @Test
    public void testGetAuthors() {
        List<Author> authors = Arrays.asList(author);
        when(repo.findAll()).thenReturn(authors);
        when(objectMapper.convertValue(author, AuthorResponseDto.class)).thenReturn(responseDto);

        List<AuthorResponseDto> result = authorService.getAuthors();

        assertEquals(1, result.size());
        assertEquals(responseDto, result.get(0));
    }

    @Test
    public void testFindById() {
        when(repo.findById(1L)).thenReturn(Optional.of(author));

        Author result = authorService.findById(1L);

        assertEquals(author, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            authorService.findById(1L);
        });
    }
}

