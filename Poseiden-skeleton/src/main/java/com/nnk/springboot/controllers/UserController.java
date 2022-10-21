package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/user/list")
    @ResponseStatus(code = HttpStatus.OK)
    public String  home(Model model){
        model.addAttribute("users", userService.getAllUsers());
        logger.info("Liste des utilisateurs chargée");
        return "user/list";
    }


    @GetMapping("/user/add")
    @ResponseStatus(code = HttpStatus.OK)
    public String addUser(User user) {
        logger.info("Page d'ajout des utilisateur chargée");
        return "user/add";
    }

    @PostMapping("/user/validate")
    @ResponseStatus(code = HttpStatus.OK)
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            userService.saveNewUser(user);
            model.addAttribute("users", userService.getAllUsers());
            logger.info("Nouvel utilisateur ajouté");
            return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        logger.info("Page pour la mise à jour d'un utilisateur chargée");
        return "user/update";
    }


    @PostMapping("/user/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String updateRating(@PathVariable("id") Integer id, @Valid User user,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }
        user.setId(id);
        userService.saveNewUser(user);
        model.addAttribute("users", userService.getAllUsers());
        logger.info("Utilisateur avec l'id "+id+" mit à jour");
        return "redirect:/user/list";
    }



    @GetMapping("user/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteUser(@PathVariable("id") Integer id, Model model){
        User u = userService.getUserById(id);
        userService.deleteUser(u.getId());
        model.addAttribute("users", userService.getAllUsers());
        logger.info("Utilisateur avec l'id "+id+" chargé");
        return "redirect:/user/list";
    }


}
