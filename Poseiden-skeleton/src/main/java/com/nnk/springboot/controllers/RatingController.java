package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin(origins="http://localhost:4200")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/rating/list")
    @ResponseStatus(code = HttpStatus.OK)
    public String home(Model model)
    {
        model.addAttribute("ratingList", ratingService.getAllRatings());
        logger.info("Liste des ratings chargée");
        return "rating/list";
    }

    @GetMapping("/rating/add")
    @ResponseStatus(code = HttpStatus.OK)
    public String addRating(Rating rating) {
        logger.info("Page d'ajout des ratings chargée");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    @ResponseStatus(code = HttpStatus.OK)
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ratingService.saveNewRating(rating);
            model.addAttribute("ratings", ratingService.getAllRatings());
            logger.info("Nouveau rating ajouté");
            return "redirect:/rating/list";
        }
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id);
        model.addAttribute("rating", rating);
        logger.info("Page pour la mise à jour d'un rating chargée");
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/update";
        }
        rating.setId(id);
        ratingService.saveNewRating(rating);
        model.addAttribute("ratings", ratingService.getAllRatings());
        logger.info("Rating avec l'id "+id+" mit à jour");
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteRating(id);
        model.addAttribute("ratings", ratingService.getAllRatings());
        logger.info("rating avec l'id "+id+" chargé");
        return "redirect:/rating/list";
    }
}




