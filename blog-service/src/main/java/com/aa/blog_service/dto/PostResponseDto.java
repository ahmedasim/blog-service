package com.aa.blog_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
	private Long postId;	
    private String text;
    private Long authorId;
    private boolean isDeleted;
}
