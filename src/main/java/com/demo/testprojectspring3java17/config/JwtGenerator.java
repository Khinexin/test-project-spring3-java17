package com.demo.testprojectspring3java17.config;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.testprojectspring3java17.dto.LoginUser;
import com.demo.testprojectspring3java17.service.UsersService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@Service
@RequiredArgsConstructor
public class JwtGenerator implements IJwtGenerator {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${app.jwttoken.message}")
	private String message;

	@Value("${jwt.millisec}")
	private String millisec;

	private final UsersService usersService;

	@PostConstruct
	protected void init() {
		secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}

	@Override
	public Map<String, String> generateToken(LoginUser user) {

		System.out.println("generateToken ... ");

		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.username()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, secret).compact();

		Map<String, String> jwtTokenGen = new HashMap<>();
		jwtTokenGen.put("token", jwtToken);
		jwtTokenGen.put("message", message);

		System.out.println(" JWT-TOKEN : " + jwtToken);

		return jwtTokenGen;
	}

}
