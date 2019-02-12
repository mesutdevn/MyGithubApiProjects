package com.githubexample;


import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GithubexampleApplication {
	
    public static void main(String args[]) {
    	 SpringApplication app = new SpringApplication(GithubexampleApplication.class);
		 app.setBannerMode(Banner.Mode.OFF);
		 app.run(args);
    }
    
    @Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
    
	@Bean
	public <T> CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			
			try {
				
				Runner r = new Runner();
				r.loadRestTemplate(restTemplate);
				r.run();
				
			} catch(RestClientException ex1) {
				System.out.println("Http hatasi olustu. Detay: " + ex1.getMessage());
			} catch (Exception e) {
				System.out.println("Baska bir hata olustu. Detay: " + e.getMessage() + " " + e.getStackTrace());
			}
			
		};
	}
	
	

}

