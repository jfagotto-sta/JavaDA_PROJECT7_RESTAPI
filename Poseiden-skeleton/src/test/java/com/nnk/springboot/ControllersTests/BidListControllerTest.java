package com.nnk.springboot.ControllersTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
public class BidListControllerTest {


    @MockBean
    public BidListService bidListService;

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
    public void bidListTest() throws Exception {

        BidList bidList = new BidList();
        bidList.setType("ab");
        bidList.setBid(50d);
        bidList.setAccount("ab");


        List<BidList> bidListList = new ArrayList<>();
        bidListList.add(bidList);


        ObjectMapper mapper = new ObjectMapper();
        String u = mapper.writeValueAsString(bidList);

        when(bidListService.getAllBidList()).thenReturn(bidListList);

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("bidLists",containsInAnyOrder(bidList)));

        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk());

        Mockito.doNothing().when(bidListService).saveNewBidList(bidList);

        mockMvc.perform(post("/bidList/validate"))
                .andExpect(redirectedUrl("/bidList/list"));

        mockMvc.perform(post("/bidList/validate").content(u).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        when(bidListService.getBidListById(anyInt())).thenReturn(bidList);

        mockMvc.perform(get("/bidList/update/{id}","4"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/bidList/update/{id}","4").
                        content(u).contentType(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrl("/bidList/list"));


        mockMvc.perform(get("/bidList/delete/{id}","4"))
                .andExpect(redirectedUrl("/bidList/list"));
    }


}


