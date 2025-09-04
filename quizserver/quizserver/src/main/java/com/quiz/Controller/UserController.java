package com.quiz.Controller;

import com.quiz.Service.UserService;
import com.quiz.model.Role;
import com.quiz.model.User;
import com.quiz.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;



    @PostMapping("/")
    public User CreateUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");
        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("Normal");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);

        return userService.createuser(user,roles);
    }

    @GetMapping("/{username}")
    public User getByUserName(@PathVariable("username") String username){
        return userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void deleteUsers(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }
}
