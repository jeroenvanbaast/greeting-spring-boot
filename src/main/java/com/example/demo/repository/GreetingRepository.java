package com.example.demo.repository;

import com.example.demo.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GreetingRepository extends BaseRepository<Greeting, String> {

    Greeting findByName(String content);
}
