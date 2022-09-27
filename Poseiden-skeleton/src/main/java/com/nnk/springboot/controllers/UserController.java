package com.nnk.springboot.controllers;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/list")
    @ResponseStatus(code = HttpStatus.OK)
    public List<User>  usersList(Model model){
        model.addAttribute("users", userRepository.findAll());
        return userRepository.findAll();
    }

    @GetMapping("/user/id")
    @ResponseStatus(code = HttpStatus.OK)
    public User getUserById(@RequestParam int id){
        return userRepository.getById(id);
    }

    @PostMapping("/user/add")
    @ResponseStatus(code = HttpStatus.OK)
    public User addUser(User user) {
        user.setPassword(PasswordHashing.getEncodedPassword(user.getPassword()));
        return userRepository.save(user);
    }



    @PostMapping("user/update")
    @ResponseStatus(code = HttpStatus.OK)
    public User updateUser(@RequestParam int id){
        User u = userRepository.getById(id);
        //u.setUsername(user.getUsername());
        //u.setFullname(user.getFullname());
        //u.setPassword(PasswordHashing.getEncodedPassword(user.getUsername()));
        //u.setRole(user.getUsername());
        //userRepository.save(u);
        //return u;
        return null;
    }

    @GetMapping("user/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteUser(@PathVariable("id") Integer id, Model model){
        User u = userRepository.getById(id);
        userRepository.delete(u);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }


}
