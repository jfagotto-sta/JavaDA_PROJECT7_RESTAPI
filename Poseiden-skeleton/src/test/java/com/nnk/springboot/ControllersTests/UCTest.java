package com.nnk.springboot.ControllersTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
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
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
@AutoConfigureMockMvc
public class UCTest {


    @MockBean
    public UserService userService;

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
    public void userListAsAdmin() throws Exception {

        User user1 = new User();
        user1.setFullname("a");
        user1.setUsername("b");
        user1.setPassword("ab");



        User user2 = new User();
        user2.setFullname("n");
        user2.setUsername("v");
        user2.setPassword("cx");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        ObjectMapper mapper = new ObjectMapper();
        String u = mapper.writeValueAsString(user1);

        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("users",containsInAnyOrder(user1,user2)));

        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk());

    when(userService.saveNewUser(user1)).thenReturn(user1);

        mockMvc.perform(post("/user/validate"))
                .andExpect(redirectedUrl("/user/list"));

        mockMvc.perform(post("/user/validate").content(u).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        when(userService.getUserById(anyInt())).thenReturn(user1);

        mockMvc.perform(get("/user/update/{id}","4"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/user/update/{id}","4").
                content(u).contentType(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrl("/user/list"));


        mockMvc.perform(get("/user/delete/{id}","4"))
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    @WithMockUser(username = "USER", roles = {"USER"})
    public void userListAsUser() throws Exception {

        mockMvc.perform(get("/user/list"))
                .andExpect(status().is4xxClientError());
    }

}


