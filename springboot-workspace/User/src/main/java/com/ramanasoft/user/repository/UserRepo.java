package com.ramanasoft.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramanasoft.user.model.User;

public interface UserRepo extends JpaRepository<User, Long>{

	Optional<User>findByEmail(String email);
	Optional<User> findByMobileNumber(String mobileNumber);
}

