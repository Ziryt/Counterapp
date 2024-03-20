package com.ziryt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/counters")
public class CounterApp {

    private final CounterRepository counterRepository;

    public CounterApp(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(CounterApp.class, args);
    }

    @GetMapping()
    public List<Counter> getCounters() {
        return counterRepository.findAll();
    }

    @GetMapping("{counterId}")
    public String getCounter(@PathVariable("counterId") Integer id) {
        return counterRepository.findById(id).toString();
    }

    record NewCounterRequest(
            String name,
            Integer initial_value,
            Integer limit,
            String color
    ){

    }
    @PostMapping("/addCounter")
    public void addCounter(@RequestBody NewCounterRequest request){
        Counter counter = new Counter();
        counter.setName(request.name());
        counter.setCount(request.initial_value());
        counter.setLimited(request.limit());
        counter.setColor(request.color());
        counterRepository.save(counter);
    }

    @PutMapping("{counterId}")
    public void changeCounter(@PathVariable("counterId") Integer id,
                           @RequestBody NewCounterRequest request){
        Counter counter = new Counter();
        counter.setId(id);
        counter.setName(request.name());
        counter.setCount(request.initial_value());
        counter.setLimited(request.limit());
        counter.setColor(request.color());
        counterRepository.save(counter);

    }

    @DeleteMapping("{counterId}")
    public void deleteCounter(@PathVariable("counterId") Integer id){
        counterRepository.deleteById(id);
    }

}
