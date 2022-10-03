package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
public class RatingTest {

    @Autowired
    private RatingService ratingService;

    @BeforeAll
    public void deleteall() {
        ratingService.deleteAll();
    }

    @Test
    public void ratingTest() {
        Rating r = new Rating();
        r.setMoodyRating("a");

        // Save
        ratingService.saveNewRating(r);
        Rating r2 = ratingService.getRatingById(1);
        assertNotNull(r2);

        // Update
        r.setMoodyRating("ab");
        ratingService.saveNewRating(r);
        assertTrue(r.getMoodyRating().equals("ab"));

        // Find
        List<Rating> listResult = ratingService.getAllRatings();
        assertTrue(listResult.size() > 0);

        // Delete
        ratingService.deleteRating(r.getId());
        assertTrue(ratingService.getAllRatings().size()==0);	}
}

