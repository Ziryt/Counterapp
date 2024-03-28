package com.ziryt.counter.controller;

import com.ziryt.counter.model.DTO.CounterDTO;
import com.ziryt.counter.model.DTO.CreateCounterRequest;
import com.ziryt.counter.model.DTO.SetLimitsRequest;
import com.ziryt.counter.model.DTO.UpdateCounterRequest;
import com.ziryt.counter.model.DTO.UpdateValueRequest;
import com.ziryt.counter.model.mapper.Mapper;
import com.ziryt.counter.service.CounterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.ziryt.counter.model.mapper.Mapper.entityToDTO;
import static com.ziryt.counter.model.mapper.Mapper.pageEntityToDTO;

@RestController
@RequestMapping("api/v1/counters")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Endpoints")
public class CounterController {

    private final CounterService counterService;

    @GetMapping("/")
    public Page<CounterDTO> getCounters(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size) {
        log.debug("get all counters");
        return pageEntityToDTO(counterService.getCounters(page, size));
    }

    @GetMapping("{counterId}")
    @ResponseBody
    public CounterDTO getCounter(@PathVariable("counterId") Integer id) {
        log.debug("get counter with id={}", id);
        return entityToDTO(counterService.getCounter(id));
    }

    @PostMapping("/")
    public CounterDTO createCounter(@RequestBody CreateCounterRequest request) {
        log.debug("create counter with name={}", request.name());
        return entityToDTO(counterService.createCounter(request));
    }

    @PostMapping("{counterId}/inc")
    public CounterDTO incrementValue(@PathVariable("counterId") Integer id){
        log.debug("increment value for counter with id={}", id);
        return entityToDTO(counterService.incrementValue(id));
    }

    @PostMapping("{counterId}/dec")
    public CounterDTO decrementValue(@PathVariable("counterId") Integer id){
        log.debug("decrement value for counter with id={}", id);
        return entityToDTO(counterService.decrementValue(id));
    }

    @PostMapping("{counterId}/update_by")
    public CounterDTO updateValueBy(@PathVariable("counterId") Integer id,
                                 @RequestBody UpdateValueRequest request) {
        log.debug("update value for counter with id={}", id);
        return entityToDTO(counterService.updateValueBy(id, request));
    }

    @PostMapping("{counterId}/value")
    public CounterDTO setValue(@PathVariable("counterId") Integer id,
                            @RequestBody UpdateValueRequest request) {
        log.debug("set value for counter with id={}", id);
        return entityToDTO(counterService.setValue(id, request));
    }

    @PostMapping("{counterId}/limits")
    public CounterDTO setLimit(@PathVariable("counterId") Integer id,
                               @RequestBody SetLimitsRequest request) {
        log.debug("set limits for counter with id={}", id);
        return entityToDTO(counterService.setLimit(id, request));
    }

    @PutMapping("{counterId}")
    public CounterDTO updateCounter(@PathVariable("counterId") Integer id,
                                 @RequestBody UpdateCounterRequest request) {
        log.debug("update counter with id={}", id);
        return entityToDTO(counterService.updateCounter(id, request));
    }

    @DeleteMapping("{counterId}")
    public void deleteCounter(@PathVariable("counterId") Integer id){
        log.debug("delete counter with id={}", id);
        counterService.deleteCounter(id);
    }

}
