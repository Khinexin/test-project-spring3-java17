package com.demo.testprojectspring3java17.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.testprojectspring3java17.dto.UsersRequest;
import com.demo.testprojectspring3java17.entity.Users;
import com.demo.testprojectspring3java17.repository.UsersRepository;
import com.demo.testprojectspring3java17.service.UsersService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppBootStrap implements CommandLineRunner {

	private final UsersRepository usersRepository;

	@Override
	public void run(String... args) throws Exception {
		if (usersRepository.count() == 0) {

			usersRepository
					.save(Users.builder().username("admin").password("admin").roles("ROLE_ADMIN,ROLE_USER").build());
			usersRepository.save(Users.builder().username("user").password("user").roles("ROLE_USER").build());
		}
	}

}
