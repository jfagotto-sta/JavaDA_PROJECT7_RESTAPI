package com.nnk.springboot.controllers;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class TradeController {


    @Autowired
    private TradeRepository tradeRepository;

    @GetMapping("/trade/list")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Trade> tradeList(){
        return tradeRepository.findAll();
    }

    @GetMapping("/trade/id")
    @ResponseStatus(code = HttpStatus.OK)
    public Trade tradeById(@RequestParam int id){
        return tradeRepository.getById(id);
    }

    @PostMapping("/trade/add")
    @ResponseStatus(code = HttpStatus.OK)
    public Trade addTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

//    @PostMapping("user/update")
//    @ResponseStatus(code = HttpStatus.OK)
//    public User updateUser(@RequestBody User user){
//        User u = userRepository.getById(user.getId());
//        u.setUsername(user.getUsername());
//        u.setFullname(user.getFullname());
//        u.setPassword(PasswordHashing.getEncodedPassword(user.getUsername()));
//        u.setRole(user.getUsername());
//        userRepository.save(u);
//        return u;
//    }

    @DeleteMapping("user/delete/id")
    @ResponseStatus(code = HttpStatus.OK)
    public Boolean deleteTrade(@RequestParam int id){
        Trade t = tradeRepository.getById(id);
        tradeRepository.delete(t);
        return true;
    }

//    @RequestMapping("/trade/list")
//    public String home(Model model)
//    {
//        // TODO: find all Trade, add to model
//        return "trade/list";
//    }
//
//    @GetMapping("/trade/add")
//    public String addUser(Trade bid) {
//        return "trade/add";
//    }
//
//    @PostMapping("/trade/validate")
//    public String validate(@Valid Trade trade, BindingResult result, Model model) {
//        // TODO: check data valid and save to db, after saving return Trade list
//        return "trade/add";
//    }
//
//    @GetMapping("/trade/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
//        // TODO: get Trade by Id and to model then show to the form
//        return "trade/update";
//    }
//
//    @PostMapping("/trade/update/{id}")
//    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
//                             BindingResult result, Model model) {
//        // TODO: check required fields, if valid call service to update Trade and return Trade list
//        return "redirect:/trade/list";
//    }
//
//    @GetMapping("/trade/delete/{id}")
//    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
//        // TODO: Find Trade by Id and delete the Trade, return to Trade list
//        return "redirect:/trade/list";
//    }
}
