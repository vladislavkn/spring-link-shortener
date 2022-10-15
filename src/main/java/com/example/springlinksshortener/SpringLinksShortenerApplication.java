package com.example.springlinksshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringLinksShortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLinksShortenerApplication.class, args);
	}

}
