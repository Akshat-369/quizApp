package com.quiz;

import com.quiz.Service.UserService;
import com.quiz.model.Role;
import com.quiz.model.User;
import com.quiz.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class QuizserverApplication implements CommandLineRunner {

	@Autowired
	public UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(QuizserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting code");

//		User user = new User();
//		user.setUsername("Akshat369");
//		user.setFirstname("Akshat");
//		user.setLastname("Limbachiya");
//		user.setEmail("akshat@gmail.com");
//		user.setProfile("default.png");
//		user.setPassword("abc");
//
//		Role role1 = new Role();
//		role1.setRoleId(44L);
//		role1.setRoleName("ADMIN");
//
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		userRoleSet.add(userRole);
//
//		User user1 = userService.createuser(user, userRoleSet);
//		System.out.println(user1);
	}
}
