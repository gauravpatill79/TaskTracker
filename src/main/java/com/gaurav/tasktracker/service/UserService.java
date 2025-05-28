package com.gaurav.tasktracker.service;


import com.gaurav.tasktracker.entity.User;
import com.gaurav.tasktracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById( long id){
        return userRepository.findById(id);
    }

    public User createUser( User user){
        return userRepository.save(user);
    }

    public User updateUser( long id , User userDetails){
        return userRepository.findById(id).map(user ->{
            user.setName(userDetails.getName());
            user.setEmail((userDetails.getEmail()));
            return userRepository.save(user);
        }).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
