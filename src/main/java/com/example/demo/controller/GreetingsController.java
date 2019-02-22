package com.example.demo.controller;

import com.example.demo.repository.GreetingRepository;
import com.example.demo.model.Greeting;
import com.example.demo.services.GreetingsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    @Autowired
    private GreetingsService greetingsService;

    @GetMapping("")
    @ApiOperation("Returns all greetings")
    public List<Greeting> all() {
        return greetingsService.findAll();
    }

    @PostMapping("/")
    @ApiOperation("Create a greeting")
    public Greeting newGreeting(@RequestBody Greeting greeting) {
        return greetingsService.save(greeting);
    }

    @GetMapping("/{name}")
    @ApiOperation("Returns a greeting by a specified name")
    public Greeting greetingByName(@PathVariable(value = "name") String name) {
        return greetingsService.findByName(name);
    }

    @PutMapping("/{id}")
    @ApiOperation("Updates a greeting by a specified name")
    public Greeting updateGreeting(@RequestBody Greeting greeting, @PathVariable("id") String id) {
      greeting.setId(id);
      return greetingsService.save(greeting);
    }

    @DeleteMapping("/")
    @ApiOperation("Deletes a greeting by a specified name")
    public void deleteGreeting(@RequestParam String id) {
        greetingsService.deleteById(id);
    }

    @ExceptionHandler()
    public void handleExeption(){

    }
}
