package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

public class Greeting {

    @Id
    @ApiModelProperty(notes = "The id of the greeting")
    private String id;
    @ApiModelProperty(notes = "The name of the person to greet")
    private String name;

    public Greeting(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
