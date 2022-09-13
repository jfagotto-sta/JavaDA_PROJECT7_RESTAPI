package com.nnk.springboot.controllers;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import javax.validation.Valid;


@Controller
public class BidListController {

   @Autowired
   private BidListRepository bidListRepository;

    @GetMapping("/bidlist/list")
    @ResponseStatus(code = HttpStatus.OK)
    public List<BidList> bidList(){
        return bidListRepository.findAll();
    }

    @GetMapping("/bidlist/id")
    @ResponseStatus(code = HttpStatus.OK)
    public BidList getUserById(@RequestParam int id){
        return bidListRepository.getById(id);
    }

    @PostMapping("/bidlist/add")
    @ResponseStatus(code = HttpStatus.OK)
    public BidList addUser(BidList bidList) {
        return bidListRepository.save(bidList);
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

    @DeleteMapping("bidlist/delete/id")
    @ResponseStatus(code = HttpStatus.OK)
    public Boolean deleteBidList(@RequestParam int id){
        BidList b = bidListRepository.getById(id);
        bidListRepository.delete(b);
        return true;
    }



//    @RequestMapping("/bidList/list")
//    public String home(Model model)
//    {
//        // TODO: call service find all bids to show to the view
//        return "bidList/list";
//    }
//
//    @GetMapping("/bidList/add")
//    public String addBidForm(BidList bid) {
//        return "bidList/add";
//    }
//
//    @PostMapping("/bidList/validate")
//    public String validate(@Valid BidList bid, BindingResult result, Model model) {
//        // TODO: check data valid and save to db, after saving return bid list
//        return "bidList/add";
//    }
//
//    @GetMapping("/bidList/update/{id}")
//    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
//        // TODO: get Bid by Id and to model then show to the form
//        return "bidList/update";
//    }
//
//    @PostMapping("/bidList/update/{id}")
//    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
//                             BindingResult result, Model model) {
//        // TODO: check required fields, if valid call service to update Bid and return list Bid
//        return "redirect:/bidList/list";
//    }
//
//    @GetMapping("/bidList/delete/{id}")
//    public String deleteBid(@PathVariable("id") Integer id, Model model) {
//        // TODO: Find Bid by Id and delete the bid, return to Bid list
//        return "redirect:/bidList/list";
//    }
}
