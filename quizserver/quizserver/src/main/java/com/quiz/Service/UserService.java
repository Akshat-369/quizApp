package com.quiz.Service;

import com.quiz.model.User;
import com.quiz.model.UserRole;

import java.util.Optional;
import java.util.Set;

public interface UserService {


    public User createuser(User user, Set<UserRole> userRoles) throws Exception;

    public User getUser(String username);

    public void deleteUser(Long userId);

    public User findByUsername(String username);

    public Optional<User> findById(Long Id);

    public void saveNewUser(User user);

}
