package com.demo.testprojectspring3java17.controllers;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.testprojectspring3java17.config.JwtGenerator;
import com.demo.testprojectspring3java17.dto.LoginUser;
import com.demo.testprojectspring3java17.dto.UsersRequest;
import com.demo.testprojectspring3java17.entity.Users;
import com.demo.testprojectspring3java17.exception.CustomException;
import com.demo.testprojectspring3java17.service.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

	private final UsersService usersService;
	private final JwtGenerator jwtGenerator;

	@PostMapping("/register")
	public ResponseEntity<?> postUser(@RequestBody UsersRequest user) {
		try {
			usersService.addUser(user);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginUser user) {
		System.out.println("login user : \t" + user.getUsername() + ", " + user.getPassword());
		try {
			if (Objects.isNull(user.getUsername()) || Objects.isNull(user.getPassword())) {
				throw new CustomException("UserName or Password is Empty");
			}
			Users userData = usersService.getUserByNameAndPassword(user);
			if (Objects.isNull(userData)) {
				throw new CustomException("UserName or Password is Invalid");
			}
			return new ResponseEntity<>(jwtGenerator.generateToken(user), HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}