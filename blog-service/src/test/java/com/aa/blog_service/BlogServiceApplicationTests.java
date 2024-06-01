package com.aa.blog_service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogServiceApplicationTests {

	@Test
    public void contextLoadsSuccessfully() {
		 assertDoesNotThrow(() -> BlogServiceApplication.main(new String[]{}));
    }

}
