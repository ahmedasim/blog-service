package com.aa.blog_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
	private Long postId;	
    private String text;
    private Long authorId;
    private boolean isDeleted;
}
