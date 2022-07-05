package com.example.springsec;

import com.example.springsec.config.DefaultDataSetter;
import com.example.springsec.entities.Privilege;
import com.example.springsec.repositories.PrivilegeRepository;
import com.example.springsec.repositories.UserRepository;
import com.example.springsec.services.PrivelegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;


@SpringBootApplication
public class SpringSecApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecApplication.class, args);
	}

	@Autowired
	private PrivelegeService privelegeService;

	@Override
	public void run(String... args) throws Exception {
		DefaultDataSetter defaultDataSetter = new DefaultDataSetter(privelegeService);

		System.out.println("App started!");
	}
}
