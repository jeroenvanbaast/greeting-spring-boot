package com.example.demo.controller;

import com.example.demo.repository.GreetingRepository;
import com.example.demo.model.Greeting;
import com.example.demo.services.GreetingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    @Autowired
    private GreetingsService greetingsService;

    @GetMapping("")
    public List<Greeting> all() {
        return greetingsService.findAll();
    }

    @PostMapping("/")
    public Greeting newGreeting(@RequestBody Greeting greeting) {
        return greetingsService.save(greeting);
    }

    @GetMapping("/{name}")
    public Greeting greetingByName(@PathVariable(value = "name") String name) {
        return greetingsService.findByName(name);
    }

    @PutMapping("/{id}")
    public Greeting updateGreeting(@RequestBody Greeting greeting, @PathVariable("id") String id) {
      greeting.setId(id);
      return greetingsService.save(greeting);
    }

    @DeleteMapping("/")
    public void deleteGreeting(@RequestParam String id) {
        greetingsService.deleteById(id);
    }

    @ExceptionHandler()
    public void handleExeption(){

    }
}
