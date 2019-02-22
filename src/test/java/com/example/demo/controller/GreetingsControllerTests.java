package com.example.demo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.Greeting;
import com.example.demo.services.GreetingsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingsController.class)
public class GreetingsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingsService service;

    @Test
    public void greetingByName() throws Exception {
        when(service.findByName("Jeroen")).thenReturn(new Greeting("1", "Jeroen"));
        this.mockMvc.perform(get("/greetings/Jeroen")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Jeroen")));
    }

    @Test
    public void allGreetings() throws Exception {
        List<Greeting> greetings = new ArrayList<>();
        greetings.add(new Greeting("1", "Karel"));
        when(service.findAll()).thenReturn(greetings);
        this.mockMvc.perform(get("/greetings/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Karel")));
    }

    @Test
    public void newGreeting() throws Exception {
        when(service.save(Mockito.any(Greeting.class))).thenReturn(new Greeting("1", "Jan"));
        this.mockMvc.perform(post("/greetings/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new Greeting("1", "Jan"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jan")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
