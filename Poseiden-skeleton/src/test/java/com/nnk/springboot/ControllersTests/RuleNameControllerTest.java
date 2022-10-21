package com.nnk.springboot.ControllersTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
public class RuleNameControllerTest {


    @MockBean
    public RuleNameService ruleNameService;

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
    public void ruleNameControllerTest() throws Exception {

        RuleName ruleName = new RuleName();
        ruleName.setName("ab");
        ruleName.setSqlStr("ab");
        ruleName.setTemplate("ab");
        ruleName.setSqlPart("ab");
        ruleName.setJson("ab");

        List<RuleName> ruleNameList = new ArrayList<>();
        ruleNameList.add(ruleName);

        ObjectMapper mapper = new ObjectMapper();
        String u = mapper.writeValueAsString(ruleName);

        when(ruleNameService.getAllRuleNames()).thenReturn(ruleNameList);

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("ruleNames",containsInAnyOrder(ruleName)));

        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk());


        mockMvc.perform(post("/ruleName/validate"))
                .andExpect(redirectedUrl("/ruleName/list"));

        mockMvc.perform(post("/ruleName/validate").content(u).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        when(ruleNameService.getRuleNameById(anyInt())).thenReturn(ruleName);

        mockMvc.perform(get("/ruleName/update/{id}","4"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/ruleName/update/{id}","4").
                        content(u).contentType(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrl("/ruleName/list"));

        //Mockito.doNothing().when(userService).deleteUser(anyInt());

        mockMvc.perform(get("/ruleName/delete/{id}","4"))
                .andExpect(redirectedUrl("/ruleName/list"));
    }


}


