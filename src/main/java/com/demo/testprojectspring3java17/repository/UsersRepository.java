package com.demo.testprojectspring3java17.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.testprojectspring3java17.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByUsernameAndPassword(String username, String password);

	Optional<Users> findByUsername(String username);

	boolean existsByUsername(String username);

	void deleteByUsername(String username);
}