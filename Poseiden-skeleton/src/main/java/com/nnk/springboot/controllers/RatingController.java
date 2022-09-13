package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @RequestMapping("/rating/list")
    public List<Rating> ratingList( )
    {
        return ratingRepository.findAll();
    }

    @GetMapping("/rating/id")
    public Rating getUserById(@RequestParam int id){
        return ratingRepository.getById(id);
    }

    @PostMapping("/rating/add")
    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @PostMapping("rating/update")
    public Rating updateUser(@RequestBody Rating rating){
        Rating r = ratingRepository.getById(rating.getId());
        r.setMoodyRating(r.getMoodyRating());
        r.setFitchRating(r.getFitchRating());
        r.setSandPRating(r.getSandPRating());
        r.setOrderNumber(r.getOrderNumber());
        return r;
    }
}




