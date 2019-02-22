package com.example.demo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.Greeting;
import com.example.demo.services.GreetingsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingsController.class)
public class GreetingsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingsService service;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(service.findByName("Jeroen")).thenReturn(new Greeting("1","Jeroen"));
        this.mockMvc.perform(get("/greetings/Jeroen")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Jeroen")));
    }
}
