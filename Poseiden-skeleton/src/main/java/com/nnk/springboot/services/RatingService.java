package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating getRatingById (Integer id){
        return ratingRepository.getById(id);
    }

    public List<Rating> getAllRatings(){
        return ratingRepository.findAll();
    }

    public void saveNewRating(Rating rating){
        ratingRepository.save(rating);
    }

    public void deleteRating(Integer id){
        Rating r = ratingRepository.getById(id);
        ratingRepository.delete(r);
    }

    public void deleteAll(){
        ratingRepository.deleteAll();
    }



}
