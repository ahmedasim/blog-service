package com.aa.blog_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorRequestDto {
	@NotBlank(message = "Author name is mandatory")
	private String name;
}
