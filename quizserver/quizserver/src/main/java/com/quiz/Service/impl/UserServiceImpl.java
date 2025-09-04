package com.quiz.Service.impl;

import com.quiz.Repository.RoleRepository;
import com.quiz.Repository.UserRepository;
import com.quiz.Service.UserService;
import com.quiz.model.User;
import com.quiz.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //creating user
    @Override
    public User createuser(User user, Set<UserRole> userRoles) throws Exception{

        User local = userRepository.findByUsername(user.getUsername());
        if (local!=null){
            System.out.println("User is already there!!");
            throw new Exception("User Already exists !!");
        }else {
            //user create
            for (UserRole ur: userRoles){
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local = userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(Long Id) {
        return userRepository.findById(Id);
    }

    @Override
    public void saveNewUser(User user) {
         userRepository.save(user);
    }


}
