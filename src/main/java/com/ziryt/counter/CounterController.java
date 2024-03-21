package com.ziryt.counter;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("api/v1/counters")
public class CounterController {

    record NewCounterRequest(
            String name,
            Integer initial_value,
            Integer limit,
            String color
    ){}
    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping()
    public List<Counter> getCounters() {
        return counterService.getCounters();
    }

    @GetMapping("{counterId}")
    public String getCounter(@PathVariable("counterId") Integer id) {
        return counterService.getCounter(id);
    }

    @PostMapping("/addCounter")
    public void addCounter(@RequestBody NewCounterRequest request){
        counterService.addCounter(request);
    }

    @PutMapping("{counterId}")
    public void updateCounter(@PathVariable("counterId") Integer id,
                              @RequestBody NewCounterRequest request){
        counterService.updateCounter(id, request);
    }

    @DeleteMapping("{counterId}")
    public void deleteCounter(@PathVariable("counterId") Integer id){
        counterService.deleteCounter(id);
    }

}
