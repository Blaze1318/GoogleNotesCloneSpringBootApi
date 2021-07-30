package com.person.googlenotesclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
*/
@SpringBootApplication
public class GooglenotescloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(GooglenotescloneApplication.class, args);
	}
	
	/*
	 * @Bean public WebMvcConfigurer corsConfigurer() { return new
	 * WebMvcConfigurer() {
	 * 
	 * @Override public void addCorsMappings(CorsRegistry registry) {
	 * registry.addMapping("/api/v1/todo").allowedOrigins("http://localhost:9000");
	 * } }; }
	 */

}
