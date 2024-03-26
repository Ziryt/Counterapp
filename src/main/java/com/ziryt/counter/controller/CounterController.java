package com.ziryt.counter.controller;

import com.ziryt.counter.model.entity.Counter;
import com.ziryt.counter.model.DTO.Records.CreateCounterRequest;
import com.ziryt.counter.model.DTO.Records.UpdateCounterRequest;
import com.ziryt.counter.model.DTO.Records.UpdateValueRequest;
import com.ziryt.counter.service.CounterService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/counters")
@AllArgsConstructor
public class CounterController {
    private final CounterService counterService;
    private final Logger logger = LoggerFactory.getLogger(CounterController.class);

    @GetMapping()
    public List<Counter> getCounters() {
        logger.trace("get all counters");
        return counterService.getCounters();
    }

    @GetMapping("{counterId}")
    @ResponseBody
    public ResponseEntity<Counter> getCounter(@PathVariable("counterId") Integer id) {
        logger.trace("get counter with id={}", id);
        Counter counter = counterService.getCounter(id);
        return ResponseEntity.status(HttpStatus.OK).body(counter);
    }

    @PostMapping("/new")
    public ResponseEntity<Counter> createCounter(@RequestBody CreateCounterRequest request) {
        logger.trace("create counter with name={}", request.name());
        Counter created = counterService.createCounter(request);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @PostMapping("inc/{counterId}")
    public Counter incrementValue(@PathVariable("counterId") Integer id){
        logger.trace("increment value for counter with id={}", id);
        return counterService.incrementValue(id);
    }

    @PostMapping("dec/{counterId}")
    public Counter decrementValue(@PathVariable("counterId") Integer id){
        logger.trace("decrement value for counter with id={}", id);
        return counterService.decrementValue(id);
    }

    @PostMapping("update/{counterId}")
    public Counter updateValue(@PathVariable("counterId") Integer id,
                               @RequestBody UpdateValueRequest request) {
        logger.trace("update value for counter with id={}", id);
        return counterService.updateValue(id, request);
    }

    @PatchMapping("set/{counterId}")
    public Counter setValue(@PathVariable("counterId") Integer id,
                            @RequestBody UpdateValueRequest request) {
        logger.trace("set value for counter with id={}", id);
        return counterService.setValue(id, request);
    }

    @PatchMapping("set_top/{counterId}")
    public Counter setTopLimit(@PathVariable("counterId") Integer id,
                               @RequestBody UpdateValueRequest request) {
        logger.trace("set top limit for counter with id={}", id);
        return counterService.setTopLimit(id, request);
    }

    @PatchMapping("set_bot/{counterId}")
    public Counter setBottomLimit(@PathVariable("counterId") Integer id,
                                  @RequestBody UpdateValueRequest request) {
        logger.trace("set bottom limit for counter with id={}", id);
        return counterService.setBottomLimit(id, request);
    }

    @PutMapping("{counterId}")
    public Counter updateCounter(@PathVariable("counterId") Integer id,
                                 @RequestBody UpdateCounterRequest request) {
        logger.trace("update counter with id={}", id);
        return counterService.updateCounter(id, request);
    }

    @DeleteMapping("{counterId}")
    public void deleteCounter(@PathVariable("counterId") Integer id){
        logger.trace("delete counter with id={}", id);
        counterService.deleteCounter(id);
    }

}
