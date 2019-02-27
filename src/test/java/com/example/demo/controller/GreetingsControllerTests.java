package com.example.demo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.Greeting;
import com.example.demo.services.GreetingsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingsController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class GreetingsControllerTests {

    @MockBean
    private GreetingsService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greetingByName() throws Exception {
        when(service.findByName("Jeroen")).thenReturn(new Greeting("1", "Jeroen"));
        this.mockMvc.perform(get("/greetings/Jeroen"))
                .andDo(print())
                .andExpect(content().string(containsString("Jeroen")))
                .andDo(document("home", responseFields(
                        fieldWithPath("name").description("The name of the user to greet."),
                        fieldWithPath("id").description("The id of the greeting"))));
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
                .andExpect(content().string(containsString("Jan")))
                .andDo(document("post", responseFields(
                        fieldWithPath("name").description("The name of the user to greet."),
                        fieldWithPath("id").description("The id of the greeting"))));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
