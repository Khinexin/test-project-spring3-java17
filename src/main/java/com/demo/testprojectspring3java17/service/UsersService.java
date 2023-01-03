package com.demo.testprojectspring3java17.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.testprojectspring3java17.dto.LoginUser;
import com.demo.testprojectspring3java17.dto.UsersRequest;
import com.demo.testprojectspring3java17.entity.Users;
import com.demo.testprojectspring3java17.exception.CustomException;
import com.demo.testprojectspring3java17.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsersService implements UserDetailsService {

	private final UsersRepository usersRepository;

	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	public Users addUser(UsersRequest user) {
		Users newUser = new Users();
		newUser.setUsername(user.getUsername());
//		newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		newUser.setPassword(user.getPassword());
		if (Objects.isNull(user.getRoles()) || user.getRoles().equals("")) {
			newUser.setRoles("ROLE_USER");
		} else {
			newUser.setRoles(user.getRoles());
		}
		return usersRepository.save(newUser);
	}

	public Users updateUser(UsersRequest user) {
		Optional<Users> existingUser = usersRepository.findByUsername(user.getUsername());

		if (existingUser.isPresent()) {
			Users newUser = existingUser.get();
			newUser.setUsername(user.getUsername());
//			newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			newUser.setPassword(user.getPassword());
			if (Objects.isNull(user.getRoles()) || user.getRoles().equals("")) {
				newUser.setRoles("ROLE_USER");
			} else {
				newUser.setRoles(user.getRoles());
			}
			return usersRepository.saveAndFlush(newUser);
		} else {
			return addUser(user);
		}

	}

	public Long countUsers() {
		return usersRepository.count();
	}

	public boolean existsByName(String name) {
		return usersRepository.existsByUsername(name);
	}

	public void deleteByName(String name) {
		usersRepository.deleteByUsername(name);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Optional<Users> appUser = usersRepository.findByUsername(username);

		if (Objects.isNull(appUser)) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		return org.springframework.security.core.userdetails.User//
				.withUsername(username)//
				.password(appUser.get().getPassword())//
				.authorities(appUser.get().getRoles().split(","))//
				.accountExpired(false)//
				.accountLocked(false)//
				.credentialsExpired(false)//
				.disabled(false)//
				.build();
	}

	public Users getUserByNameAndPassword(LoginUser user) throws CustomException {

		System.out.println("getUserByNameAndPassword ... ");

		Users users = usersRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

		if (Objects.isNull(users)) {
			throw new CustomException("Invalid username and password");
		}
		System.out.println(users.getUsername() + " - " + users.getPassword() + " - " + users.getRoles());
		return users;
	}
}