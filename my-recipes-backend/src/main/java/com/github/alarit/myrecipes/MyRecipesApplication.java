package com.github.alarit.myrecipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MyRecipesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyRecipesApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder app) {
		return app.sources(MyRecipesApplication.class);
	}
}