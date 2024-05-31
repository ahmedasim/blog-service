package com.aa.blog_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aa.blog_service.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}
