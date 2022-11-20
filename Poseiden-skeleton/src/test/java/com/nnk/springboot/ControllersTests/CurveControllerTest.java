package com.nnk.springboot.ControllersTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.CurvePointService;
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
public class CurveControllerTest {


    @MockBean
    public CurvePointService curvePointService;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setup() {

        ConfigurableEnvironment environment = new StandardEnvironment();
        environment.setActiveProfiles("prod");
    }

    @Test
    @WithMockUser(username = "USER")
    public void curvepointsControllerTest() throws Exception {



        CurvePoint curvePoint1 = new CurvePoint();
        curvePoint1.setTerm(50);

        CurvePoint curvePoint2 = new CurvePoint();
        curvePoint1.setTerm(100);




        List<CurvePoint> curvePointList = new ArrayList<>();
        curvePointList.add(curvePoint1);
        curvePointList.add(curvePoint2);


        ObjectMapper mapper = new ObjectMapper();
        String u = mapper.writeValueAsString(curvePoint1);


        when(curvePointService.getAllCurvePoints()).thenReturn(curvePointList);

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk());
               // .andExpect(model().attribute("curvePoint",containsInAnyOrder(curvePoint1,curvePoint2)));

        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/curvePoint/validate"))
                .andExpect(redirectedUrl("/curvePoint/list"));

        mockMvc.perform(post("/curvePoint/validate").content(u).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        when(curvePointService.getCurvePoint(anyInt())).thenReturn(curvePoint1);

        mockMvc.perform(get("/curvePoint/update/{id}","4"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/curvePoint/update/{id}","4").
                        content(u).contentType(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrl("/curvePoint/list"));


        mockMvc.perform(get("/curvePoint/delete/{id}","4"))
                .andExpect(redirectedUrl("/curvePoint/list"));
    }



}
