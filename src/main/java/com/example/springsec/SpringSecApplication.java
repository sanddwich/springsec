package com.example.springsec;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSecApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("App started!");
	}
}
