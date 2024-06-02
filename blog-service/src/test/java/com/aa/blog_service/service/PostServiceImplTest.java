package com.aa.blog_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

import com.aa.blog_service.dto.PostRequestDto;
import com.aa.blog_service.dto.PostResponseDto;
import com.aa.blog_service.entity.Author;
import com.aa.blog_service.entity.Post;
import com.aa.blog_service.exception.ResourceNotFoundException;
import com.aa.blog_service.repo.PostRepo;
import com.aa.blog_service.service.impl.PostServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @Mock
    private PostRepo repo;

    @Mock
    private AuthorService authorService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PostServiceImpl postService;

    private Post post;
    private Author author;
    private PostRequestDto requestDto;
    private PostResponseDto responseDto;
    private PostResponseDto responseDtoUpdated;

    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setAuthorId(1L);
        author.setName("Asim Ahmed");

        post = new Post();
        post.setPostId(1L);
        post.setText("Post Text");
        post.setAuthor(author);
        post.setDeleted(false);

        requestDto = new PostRequestDto();
        requestDto.setText("Post Text");
        requestDto.setAuthorId(1L);

        responseDto = new PostResponseDto();
        responseDto.setPostId(1L);
        responseDto.setText("Post Text");
        responseDto.setAuthorId(1L);
        
        responseDtoUpdated = new PostResponseDto();
        responseDtoUpdated.setPostId(1L);
        responseDtoUpdated.setText("Updated Text");
        responseDtoUpdated.setAuthorId(1L);
    }

    @Test
    public void testSavePost() {
        when(authorService.findById(1L)).thenReturn(author);
        when(repo.save(any(Post.class))).thenReturn(post);
        when(objectMapper.convertValue(any(Post.class), eq(PostResponseDto.class))).thenReturn(responseDto);

        PostResponseDto result = postService.savePost(requestDto);

        assertEquals(responseDto, result);
    }

    @Test
    public void testUpdatePost() {
        when(repo.findById(1L)).thenReturn(Optional.of(post));
        when(repo.save(post)).thenReturn(post);
        when(objectMapper.convertValue(post, PostResponseDto.class)).thenReturn(responseDtoUpdated);

        PostRequestDto updatedRequestDto = new PostRequestDto();
        updatedRequestDto.setText("Updated Text");

        PostResponseDto result = postService.updatePost(updatedRequestDto, 1L);

        assertEquals("Updated Text", result.getText());
    }

    @Test
    public void testDeletePost() {
        when(repo.findById(1L)).thenReturn(Optional.of(post));

        postService.deletePost(1L);

        verify(repo).save(post);
        assertEquals(true, post.isDeleted());
    }

    @Test
    public void testGetPostById() {
        when(repo.findById(1L)).thenReturn(Optional.of(post));
        when(objectMapper.convertValue(post, PostResponseDto.class)).thenReturn(responseDto);

        PostResponseDto result = postService.getPostById(1L);

        assertEquals(responseDto, result);
    }

    @Test
    public void testGetPosts() {
        List<Post> posts = Arrays.asList(post);
        when(repo.findAll()).thenReturn(posts);
        when(objectMapper.convertValue(post, PostResponseDto.class)).thenReturn(responseDto);

        List<PostResponseDto> result = postService.getPosts();

        assertEquals(1, result.size());
        assertEquals(responseDto, result.get(0));
    }

    @Test
    public void testGetPostsByAuthorId() {
        when(authorService.findById(1L)).thenReturn(author);
        when(repo.findByAuthor(author)).thenReturn(Arrays.asList(post));
        when(objectMapper.convertValue(post, PostResponseDto.class)).thenReturn(responseDto);

        List<PostResponseDto> result = postService.getPostsByAuthorId(1L);

        assertEquals(1, result.size());
        assertEquals(responseDto, result.get(0));
    }

    @Test
    public void testGetAuthorPostsByAuthorIdAndDeleted() {
        when(authorService.findById(1L)).thenReturn(author);
        when(repo.findByAuthorAndIsDeleted(author, false)).thenReturn(Arrays.asList(post));
        when(objectMapper.convertValue(post, PostResponseDto.class)).thenReturn(responseDto);

        List<PostResponseDto> result = postService.getAuthorPostsByAuthorIdAndDeleted(1L, false);

        assertEquals(1, result.size());
        assertEquals(responseDto, result.get(0));
    }

    @Test
    public void testFindById() {
        when(repo.findById(1L)).thenReturn(Optional.of(post));

        Post result = postService.findById(1L);

        assertEquals(post, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(repo.findById(0L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            postService.findById(0L);
        });

        assertEquals("Post not found!", exception.getMessage());
    }
}
