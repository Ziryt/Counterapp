package com.ziryt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Counterapp {
    public static void main(String[] args) {
        SpringApplication.run(Counterapp.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
