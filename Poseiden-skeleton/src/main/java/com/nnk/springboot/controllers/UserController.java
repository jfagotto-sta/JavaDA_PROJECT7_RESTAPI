package com.nnk.springboot.controllers;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.Rating;
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
import javax.validation.Valid;
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

    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            userRepository.save(user);
            model.addAttribute("users", userRepository.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }



    @PostMapping("/user/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid User user,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.getById(id);
        model.addAttribute("user", user);
        return "user/update";
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
