package com.nnk.springboot.ControllersTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
@AutoConfigureMockMvc
public class RatingControllerTest {


    @MockBean
    public RatingService ratingService;

    @Autowired
    private MockMvc mockMvc;

    private MockMvc mockMvcAdmin;

    @BeforeEach
    public void setup() {

        ConfigurableEnvironment environment = new StandardEnvironment();
        environment.setActiveProfiles("prod");
    }

    @Test
    @WithMockUser(username = "ADMIN", roles = {"ADMIN"})
    public void ratingControllerTest() throws Exception {

        Rating rating = new Rating();
        rating.setMoodysRating("ab");
        rating.setSandPRating("ab");
        rating.setFitchRating("ab");

        Rating rating2 = new Rating();
        rating.setMoodysRating("ab");
        rating.setSandPRating("ab");
        rating.setFitchRating("ab");

        List<Rating> ratingsList = new ArrayList<>();
        ratingsList.add(rating);
        ratingsList.add(rating2);

        ObjectMapper mapper = new ObjectMapper();
        String u = mapper.writeValueAsString(rating);

        when(ratingService.getAllRatings()).thenReturn(ratingsList);

        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("ratingList",containsInAnyOrder(rating,rating2)));

        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/rating/validate"))
                .andExpect(redirectedUrl("/rating/list"));

        mockMvc.perform(post("/rating/validate").content(u).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        when(ratingService.getRatingById(anyInt())).thenReturn(rating);

        mockMvc.perform(get("/rating/update/{id}","4"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/rating/update/{id}","4").
                        content(u).contentType(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrl("/rating/list"));

        //Mockito.doNothing().when(userService).deleteUser(anyInt());

        mockMvc.perform(get("/rating/delete/{id}","4"))
                .andExpect(redirectedUrl("/rating/list"));
    }



}


