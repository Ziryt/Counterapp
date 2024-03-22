package com.ziryt.counter;

import com.ziryt.DTO.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.ziryt.DTO.Records.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/counters")
public class CounterController {
    private final CounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping()
    public List<Counter> getCounters() {
        return counterService.getCounters();
    }

    @GetMapping("{counterId}")
    @ResponseBody
    public Counter getCounter(@PathVariable("counterId") Integer id) {
        return counterService.getCounter(id);
    }

    @PostMapping("/new")
    public void addCounter(@RequestBody NewCounterRequest request){
        counterService.addCounter(request);
    }

    @PostMapping("inc/{counterId}")
    public Counter incrementValue(@PathVariable("counterId") Integer id){
        return counterService.incrementValue(id);
    }

    @PostMapping("dec/{counterId}")
    public Counter decrementValue(@PathVariable("counterId") Integer id){
        return counterService.decrementValue(id);
    }

    @PostMapping("set_value/{counterId}")
    public Counter updateValue(@PathVariable("counterId") Integer id,
                                  @RequestBody UpdateValueRequest request){
        return counterService.updateValue(id, request);
    }

    @PutMapping("{counterId}")
    public void updateCounter(@PathVariable("counterId") Integer id,
                              @RequestBody CounterRequest request){
        counterService.updateCounter(id, request);
    }

    @DeleteMapping("{counterId}")
    public void deleteCounter(@PathVariable("counterId") Integer id){
        counterService.deleteCounter(id);
    }

}
