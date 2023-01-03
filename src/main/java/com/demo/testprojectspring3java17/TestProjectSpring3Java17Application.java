package com.demo.testprojectspring3java17;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.testprojectspring3java17.dto.UsersRequest;
import com.demo.testprojectspring3java17.service.UsersService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
public class TestProjectSpring3Java17Application{


	public static void main(String[] args) {
		SpringApplication.run(TestProjectSpring3Java17Application.class, args);
	}

}
