package com.aa.blog_service.dto.common;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
	
	private boolean success;
	private String message;
	private T response;
	
	private Timestamp timestamp = Timestamp.from(Instant.now()); 
	
	private List<ApiError> apiErrors;
	
}
