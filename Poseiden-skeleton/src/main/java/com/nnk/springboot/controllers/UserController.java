package com.nnk.springboot.controllers;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/list")
    public List<User>  usersList(){
       return userRepository.findAll();
    }

    @GetMapping("/user/id")
    public User getUserById(@RequestParam int id){
        return userRepository.getById(id);
    }

    @PostMapping("/user/add")
    public User addUser(User user) {
        user.setPassword(PasswordHashing.getEncodedPassword(user.getPassword()));
        return userRepository.save(user);
    }

    @PostMapping("user/update")
    public User updateUser(@RequestBody User user){
        User u = userRepository.getById(user.getId());
        u.setUsername(user.getUsername());
        u.setFullname(user.getFullname());
        u.setPassword(PasswordHashing.getEncodedPassword(user.getUsername()));
        u.setRole(user.getUsername());
    userRepository.save(u);
    return u;
    }

    @DeleteMapping("user/delete/id")
    public Boolean deleteUser(@RequestParam int id){
    User u = userRepository.getById(id);
    userRepository.delete(u);
        return true;
    }

//
//    @PostMapping("/user/validate")
//    public String validate(@Valid User user, BindingResult result, Model model) {
//        if (!result.hasErrors()) {
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            user.setPassword(encoder.encode(user.getPassword()));
//            userRepository.save(user);
//            model.addAttribute("users", userRepository.findAll());
//            return "redirect:/user/list";
//        }
//        return "user/add";
//    }
//
//    @GetMapping("/user/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        user.setPassword("");
//        model.addAttribute("user", user);
//        return "user/update";
//    }
//
//    @PostMapping("/user/update/{id}")
//    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "user/update";
//        }
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        user.setPassword(encoder.encode(user.getPassword()));
//        user.setId(id);
//        userRepository.save(user);
//        model.addAttribute("users", userRepository.findAll());
//        return "redirect:/user/list";
//    }
//
//    @GetMapping("/user/delete/{id}")
//    public String deleteUser(@PathVariable("id") Integer id, Model model) {
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        userRepository.delete(user);
//        model.addAttribute("users", userRepository.findAll());
//        return "redirect:/user/list";
//    }
}
