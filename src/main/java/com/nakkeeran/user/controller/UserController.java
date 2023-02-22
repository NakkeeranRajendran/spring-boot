package com.nakkeeran.user.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nakkeeran.user.dto.LoginDTO;
import com.nakkeeran.user.dto.Status;
import com.nakkeeran.user.entities.User;
import com.nakkeeran.user.repositories.UserRepository;
import com.nakkeeran.user.service.UserService;
@Controller
@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	


	@GetMapping("/login")
    public String home(){
        return "Hello World!";
    }
	
	@PostMapping("/signup")
	@CrossOrigin(origins = "http://localhost:3000")
	
	public ResponseEntity<User> saveStudent1(@RequestBody User user) {
		String hashpass = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(hashpass);
		return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
	
	@PostMapping("/users/signin")
    public 	ResponseEntity<User> signUser( @RequestBody User user) {
        List<User> users = userRepository.findAll();
        for (User other : users) {
        	System.out.println(other.getEmail());
            if (other.getEmail().equals(user.getEmail()) && other.getPassword().equals(user.getPassword())) {
            	return new ResponseEntity<>(user, HttpStatus.ALREADY_REPORTED);
            }
        }
        user.setLoggedIn(true);
        String otp = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(otp);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }
	
	 @PostMapping("/users/login")
	 @RequestMapping(path = "district")
	 @CrossOrigin(origins = "http://localhost:3000")
	    public Long loginUser( @RequestBody LoginDTO user) {
	        List<User> other = userRepository.findAll();
	        System.out.println(other.size());
	        for(var i=0; i<other.size(); i++) {
	        	if (other.get(i).getEmail().equals(user.getEmail()) && other.get(i).getPassword().equals(user.getPassword())) {
	            	System.out.println(other.get(i).getEmail()+" == "+user.getEmail() +" && "+ other.get(i).getPassword()+" == "+user.getPassword() );
	                user.setLoggedIn(true);
	                return other.get(i).getId();
	                
	            }
	        }
	        	
//alternate working method
//	        for (User users  : other) {
//	            if (other.getEmail().equals(user.getEmail()) && other.getPassword().equals(user.getPassword())) {
//	            	System.out.println(other.getEmail()+" == "+user.getEmail() +" && "+ other.getPassword()+" == "+user.getPassword() );
//	                user.setLoggedIn(true);
//	                return Status.SUCCESS;
//	            }
//	        }
	        return (long) 0;
	    }
	
	 
	 @PostMapping("/users/logout")
	 @CrossOrigin(origins = "http://localhost:3000")
	    public Status logUserOut( @RequestBody User user) {
	        List<User> users = userRepository.findAll();
	        for (User other : users) {
	            if (other.getEmail().equals(user.getEmail())) {
	                user.setLoggedIn(false);
	                userRepository.save(user);
	                return Status.SUCCESS;
	            }
	        }
	        return Status.FAILURE;
	 }
	 
	 @PostMapping("/users/update")
	 @CrossOrigin(origins = "http://localhost:3000")
	 public void update(User user) {
	        userRepository
	                .findById(user.getId()) 
	                .ifPresent(newUser -> {
	                	newUser.setFirstname(user.getFirstname());
	                	newUser.setLastname(user.getLastname());
	                	newUser.setEmail(user.getEmail());
	                	newUser.setPhone(user.getPhone());
	                    userRepository.save(newUser);
	                });
	    }
	
	
	
}
