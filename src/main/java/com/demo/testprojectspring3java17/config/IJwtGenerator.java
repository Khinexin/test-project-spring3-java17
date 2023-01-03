package com.demo.testprojectspring3java17.config;

import java.util.Map;

import com.demo.testprojectspring3java17.dto.LoginUser;

public interface IJwtGenerator {

	Map<String, String> generateToken(LoginUser users);
}