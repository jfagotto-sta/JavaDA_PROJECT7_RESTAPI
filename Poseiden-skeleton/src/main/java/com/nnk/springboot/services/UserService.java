package com.nnk.springboot.services;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById (Integer id){
        return userRepository.getById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveNewUser(User user){
        user.setPassword(PasswordHashing.getEncodedPassword(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    public void deleteUser(Integer id){
    //   Iterable<User> u = (Iterable<User>) userRepository.getById(id);
        userRepository.deleteById(id);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }
}
