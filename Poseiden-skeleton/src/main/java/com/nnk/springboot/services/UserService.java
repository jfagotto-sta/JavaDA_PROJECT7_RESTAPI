package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;


    public User getUserById (Integer id){
        return userRepository.getById(id);
    }

    public User getUserByUsername (String name){
        return userRepository.findByUsername(name);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User saveNewUser(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
       System.out.println(user.getPassword());
        userRepository.saveAndFlush(user);
        return user;
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }

    public User complex(int id) {
        User user = userRepository.getById(id);
        user.setRole("coucou");
        return user;
    }
}
