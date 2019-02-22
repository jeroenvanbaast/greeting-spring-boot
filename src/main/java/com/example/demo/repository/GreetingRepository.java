package com.example.demo.repository;

import com.example.demo.model.Greeting;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GreetingRepository extends MongoRepository<Greeting, String> {

    Greeting findByName(String content);
}
