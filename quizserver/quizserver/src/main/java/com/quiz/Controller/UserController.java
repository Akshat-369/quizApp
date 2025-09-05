package com.quiz.Controller;

import com.quiz.Service.UserService;
import com.quiz.model.Role;
import com.quiz.model.User;
import com.quiz.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
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

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        String username = user.getUsername();
        User userInDb = userService.findByUsername(username);

        if (userInDb == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with username '" + username + "' not found");
        }

        // Update fields
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userInDb.setFirstname(user.getFirstname());
        userInDb.setLastname(user.getLastname());
        userInDb.setEmail(user.getEmail());
        userInDb.setPhone(user.getPhone());
        userInDb.setProfile(user.getProfile());

        userService.saveNewUser(userInDb);
        return ResponseEntity.ok("User updated successfully");
    }

    @ExceptionHandler(UserPrincipalNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserPrincipalNotFoundException exception)
    {
        return ResponseEntity.badRequest().build();
    }

}
