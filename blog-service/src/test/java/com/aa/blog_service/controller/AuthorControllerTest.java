package com.aa.blog_service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.aa.blog_service.dto.AuthorRequestDto;
import com.aa.blog_service.dto.AuthorResponseDto;
import com.aa.blog_service.exception.ResourceNotFoundException;
import com.aa.blog_service.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AuthorService authorService;
    
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSaveAuthor() throws Exception {
        AuthorResponseDto responseDto = new AuthorResponseDto();
        responseDto.setAuthorId(1L);
        responseDto.setName("Asim Ahmed");

        AuthorRequestDto requestDto = new AuthorRequestDto();
        requestDto.setName("Asim Ahmed");

        Mockito.when(authorService.saveAuthor(any(AuthorRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response.name").value("Asim Ahmed"))
                .andExpect(jsonPath("$.message").value("Author saved successfully"));
    }
    
    @Test
    public void testSaveAuthorWhenThrowGlobalException() throws Exception {
         AuthorRequestDto requestDto = new AuthorRequestDto();
         requestDto.setName("Asim Ahmed");

        when(authorService.saveAuthor(any(AuthorRequestDto.class))).thenThrow(new RuntimeJsonMappingException("Failed: Unrecognized field"));
        mockMvc.perform(put("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)))
        		.andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false));
    }
    
    @Test
    public void testSaveAuthorWhenInvalidName() throws Exception {
        AuthorResponseDto responseDto = new AuthorResponseDto();
        responseDto.setAuthorId(1L);
        responseDto.setName("");

        AuthorRequestDto requestDto = new AuthorRequestDto();
        requestDto.setName("");

        Mockito.when(authorService.saveAuthor(any(AuthorRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/authors")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }
    
    @Test
    public void testUpdateAuthor() throws Exception {
        AuthorResponseDto responseDto = new AuthorResponseDto();
        responseDto.setAuthorId(1L);
        responseDto.setName("Asim Ahmed Updated");

        AuthorRequestDto requestDto = new AuthorRequestDto();
        requestDto.setName("Asim Ahmed Updated");

        Mockito.when(authorService.updateAuthor(any(AuthorRequestDto.class), anyLong())).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/authors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response.name").value("Asim Ahmed Updated"))
                .andExpect(jsonPath("$.message").value("Author updated successfully"));
    }
    
    @Test
    public void testUpdateAuthorWhenInvalidAuthorId() throws Exception {
        AuthorRequestDto requestDto = new AuthorRequestDto();
        requestDto.setName("Asim Ahmed Updated");

        when(authorService.updateAuthor(any(AuthorRequestDto.class), anyLong())).thenThrow(new ResourceNotFoundException("Author not found!"));
        mockMvc.perform(put("/api/v1/authors/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)))
                .andExpect(jsonPath("$.success").value(false));
    }
    
    
    @Test
    public void testDeleteAuthor() throws Exception {
        mockMvc.perform(delete("/api/v1/authors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Author deleted successfully"));

        Mockito.verify(authorService, Mockito.times(1)).deleteAuthor(anyLong());
    }

    @Test
    public void testGetAuthorById() throws Exception {
        AuthorResponseDto responseDto = new AuthorResponseDto();
        responseDto.setAuthorId(1L);
        responseDto.setName("Asim Ahmed");

        Mockito.when(authorService.getAuthorById(anyLong())).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/authors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response.name").value("Asim Ahmed"))
                .andExpect(jsonPath("$.message").value("Author fetched successfully"));
    }

    @Test
    public void testGetAuthors() throws Exception {
        AuthorResponseDto responseDto1 = new AuthorResponseDto();
        responseDto1.setAuthorId(1L);
        responseDto1.setName("Asim Ahmed");

        AuthorResponseDto responseDto2 = new AuthorResponseDto();
        responseDto2.setAuthorId(2L);
        responseDto2.setName("Taha Siddiqui");

        List<AuthorResponseDto> responseList = Arrays.asList(responseDto1, responseDto2);

        Mockito.when(authorService.getAuthors()).thenReturn(responseList);

        mockMvc.perform(get("/api/v1/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response[0].name").value("Asim Ahmed"))
                .andExpect(jsonPath("$.response[1].name").value("Taha Siddiqui"))
                .andExpect(jsonPath("$.message").value("Authors fetched successfully"));
    }
    
    @Test
    public void testGetAuthorByIdWhenInvalidId() throws Exception {
        when(authorService.getAuthorById(0L)).thenThrow(new ResourceNotFoundException("Author not found!"));
        mockMvc.perform(get("/api/v1/authors/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

}
