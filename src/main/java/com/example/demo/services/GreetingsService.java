package com.example.demo.services;

import com.example.demo.model.Greeting;
import com.example.demo.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingsService {

    @Autowired
    private GreetingRepository repository;

    public List<Greeting> findAll() {
        return repository.findAll();
    }

    public Greeting save(Greeting greeting) {
        return repository.save(greeting);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Greeting findByName(String name){
        return repository.findByName(name);
    }
}
