package com.aa.blog_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {
	@NotNull(message = "Author ID is mandatory")
	@Min(value = 1, message = "Author ID must be greater than 0")
	private Long authorId;
	@NotBlank(message = "Post text is mandatory")
    private String text;
}
