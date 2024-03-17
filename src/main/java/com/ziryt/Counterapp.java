package com.ziryt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Counterapp {
    public static void main(String[] args) {
        SpringApplication.run(Counterapp.class, args);
    }

    @GetMapping("/hello")
    public HelloResponse hello() {
        return new HelloResponse(
                "Hello",
                List.of(1, 2, 3, 4, 5),
                new Person("me"));
    }

    record Person(String name){}
    record HelloResponse(
            String tello,
            List<Integer> numbers,
            Person person
    ){

    }
}
