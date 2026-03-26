package com.dauphine.blocker;

import com.dauphine.blocker.Model.Post;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@OpenAPIDefinition(
		info= @Info(
				title = "Blogger box backend",
				description = "Backend for the blogger box",
				contact = @Contact(name = "Jules", email = "julesbobo2004@gmail.com"),
				version = "1.0"

		)
)
public class BlockerBoxBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockerBoxBackendApplication.class, args);
	}

	public static interface PostService {

		public Post createPost(UUID id, String name, String description, UUID categoryId);
		public Post getPostById(UUID id);
		public void deletePost(UUID id);
		public Post updatePost(UUID id, String name, String description, UUID categoryId);
		public void deleteAllPosts();
		public List<Post> GetAll();
		public List<Post> GetAllbyCategory(UUID CategoryID);


	}
}
