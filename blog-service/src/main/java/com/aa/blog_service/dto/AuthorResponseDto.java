package com.aa.blog_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorResponseDto {
	private Long authorId;
	private String name;
}
