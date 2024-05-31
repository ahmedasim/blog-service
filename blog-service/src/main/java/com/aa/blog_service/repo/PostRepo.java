package com.aa.blog_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aa.blog_service.entity.Author;
import com.aa.blog_service.entity.Post;

public interface PostRepo extends JpaRepository<Post, Long> {
	List<Post> findByAuthor(Author author);
	List<Post> findByAuthorAndIsDeleted(Author author, boolean isDeleted);
}
