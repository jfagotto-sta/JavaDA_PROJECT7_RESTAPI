package com.nnk.springboot.ControllersTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.TradeService;
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
public class TradeControllerTest {


    @MockBean
    public TradeService tradeService;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {

        ConfigurableEnvironment environment = new StandardEnvironment();
        environment.setActiveProfiles("prod");
    }

    @Test
    @WithMockUser(username = "ADMIN",roles = {"ADMIN"})
    public void tradeCOntrollerTest() throws Exception {

       Trade trade1 = new Trade();
       trade1.setTradeId(1);
       trade1.setType("ab");
       trade1.setBuyQuantity(50d);
       trade1.setAccount("ab");


        Trade trade2 = new Trade("z","n");

        List<Trade> tradeList = new ArrayList<>();
        tradeList.add(trade1);
        tradeList.add(trade2);

        ObjectMapper mapper = new ObjectMapper();
        String t = mapper.writeValueAsString(trade1);


        when(tradeService.getAllTrades()).thenReturn(tradeList);


        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("trades",containsInAnyOrder(trade1,trade2)));

        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/trade/validate"))
               .andExpect(redirectedUrl("/trade/list"));

        Mockito.doNothing().when(tradeService).saveTrade(trade1);


        mockMvc.perform(post("/trade/validate").contentType(MediaType.APPLICATION_JSON).content(t))
                .andExpect(status().isOk())
                .andExpect(redirectedUrl("/trade/list"));

       when(tradeService.getTradeById(anyInt())).thenReturn(trade1);

        mockMvc.perform(get("/trade/update/{id}","4"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/trade/update/{id}","4").
                        content(t).contentType(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrl("/trade/list"));


        mockMvc.perform(get("/trade/delete/{id}","4"))
                .andExpect(redirectedUrl("/trade/list"));
    }
}


