package com.example.springsec;

import com.example.springsec.config.DefaultDataSetter;
import com.example.springsec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringSecApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("App started!");
	}
}
