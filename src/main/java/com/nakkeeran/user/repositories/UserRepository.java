package com.nakkeeran.user.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nakkeeran.user.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findAll();

	Optional<User> findByEmailAndPassword(String email, String password);
	
}
