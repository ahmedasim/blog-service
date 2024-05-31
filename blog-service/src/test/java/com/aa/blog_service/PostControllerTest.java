package com.aa.blog_service;

import com.aa.blog_service.controller.PostController;
import com.aa.blog_service.dto.PostRequestDto;
import com.aa.blog_service.dto.PostResponseDto;
import com.aa.blog_service.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSavePost() throws Exception {
        PostResponseDto responseDto = new PostResponseDto();
        responseDto.setPostId(1L);
        responseDto.setText("Sample Post");

        PostRequestDto requestDto = new PostRequestDto();
        requestDto.setText("Sample Post");
        requestDto.setAuthorId(1L);

        Mockito.when(postService.savePost(any(PostRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response.text").value("Sample Post"))
                .andExpect(jsonPath("$.message").value("Post saved successfully"));
    }

    @Test
    public void testUpdatePost() throws Exception {
        PostResponseDto responseDto = new PostResponseDto();
        responseDto.setPostId(1L);
        responseDto.setText("Updated Post");

        PostRequestDto requestDto = new PostRequestDto();
        requestDto.setText("Updated Post");
        requestDto.setAuthorId(1L);

        Mockito.when(postService.updatePost(any(PostRequestDto.class), anyLong())).thenReturn(responseDto);

        mockMvc.perform(put("/api/v1/posts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response.text").value("Updated Post"))
                .andExpect(jsonPath("$.message").value("Post updated successfully"));
    }

    @Test
    public void testDeletePost() throws Exception {
        mockMvc.perform(delete("/api/v1/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Post deleted successfully"));

        Mockito.verify(postService, Mockito.times(1)).deletePost(anyLong());
    }

    @Test
    public void testGetPostById() throws Exception {
        PostResponseDto responseDto = new PostResponseDto();
        responseDto.setPostId(1L);
        responseDto.setText("Sample Post");

        Mockito.when(postService.getPostById(anyLong())).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response.text").value("Sample Post"))
                .andExpect(jsonPath("$.message").value("Post fetched successfully"));
    }

    @Test
    public void testFindAll() throws Exception {
        PostResponseDto responseDto1 = new PostResponseDto();
        responseDto1.setPostId(1L);
        responseDto1.setText("Sample Post 1");

        PostResponseDto responseDto2 = new PostResponseDto();
        responseDto2.setPostId(2L);
        responseDto2.setText("Sample Post 2");

        List<PostResponseDto> responseList = Arrays.asList(responseDto1, responseDto2);

        Mockito.when(postService.getPosts()).thenReturn(responseList);

        mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response[0].text").value("Sample Post 1"))
                .andExpect(jsonPath("$.response[1].text").value("Sample Post 2"))
                .andExpect(jsonPath("$.message").value("Posts fetched successfully"));
    }

    @Test
    public void testGetPostsByAuthorId() throws Exception {
        PostResponseDto responseDto1 = new PostResponseDto();
        responseDto1.setPostId(1L);
        responseDto1.setText("Sample Post 1");

        PostResponseDto responseDto2 = new PostResponseDto();
        responseDto2.setPostId(2L);
        responseDto2.setText("Sample Post 2");

        List<PostResponseDto> responseList = Arrays.asList(responseDto1, responseDto2);

        Mockito.when(postService.getPostsByAuthorId(anyLong())).thenReturn(responseList);

        mockMvc.perform(get("/api/v1/posts/author-posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response[0].text").value("Sample Post 1"))
                .andExpect(jsonPath("$.response[1].text").value("Sample Post 2"))
                .andExpect(jsonPath("$.message").value("Posts fetched successfully"));
    }

    @Test
    public void testGetDeletedPostByAuthor() throws Exception {
        PostResponseDto responseDto1 = new PostResponseDto();
        responseDto1.setPostId(1L);
        responseDto1.setText("Deleted Post 1");

        PostResponseDto responseDto2 = new PostResponseDto();
        responseDto2.setPostId(2L);
        responseDto2.setText("Deleted Post 2");

        List<PostResponseDto> responseList = Arrays.asList(responseDto1, responseDto2);

        Mockito.when(postService.getAuthorPostsByAuthorIdAndDeleted(anyLong(), Mockito.eq(true))).thenReturn(responseList);

        mockMvc.perform(get("/api/v1/posts/author-deleted-posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response[0].text").value("Deleted Post 1"))
                .andExpect(jsonPath("$.response[1].text").value("Deleted Post 2"))
                .andExpect(jsonPath("$.message").value("Posts fetched successfully"));
    }

    @Test
    public void testGetActivePostByAuthor() throws Exception {
        PostResponseDto responseDto1 = new PostResponseDto();
        responseDto1.setPostId(1L);
        responseDto1.setText("Active Post 1");

        PostResponseDto responseDto2 = new PostResponseDto();
        responseDto2.setPostId(2L);
        responseDto2.setText("Active Post 2");

        List<PostResponseDto> responseList = Arrays.asList(responseDto1, responseDto2);

        Mockito.when(postService.getAuthorPostsByAuthorIdAndDeleted(anyLong(), Mockito.eq(false))).thenReturn(responseList);

        mockMvc.perform(get("/api/v1/posts/author-active-posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.response[0].text").value("Active Post 1"))
                .andExpect(jsonPath("$.response[1].text").value("Active Post 2"))
                .andExpect(jsonPath("$.message").value("Posts fetched successfully"));
    }
}
