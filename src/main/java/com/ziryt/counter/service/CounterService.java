package com.ziryt.counter.service;

import com.ziryt.counter.model.entity.Counter;
import com.ziryt.counter.model.DTO.Records.CreateCounterRequest;
import com.ziryt.counter.model.DTO.Records.UpdateCounterRequest;
import com.ziryt.counter.model.DTO.Records.UpdateValueRequest;
import com.ziryt.counter.controller.CounterController;
import com.ziryt.counter.repository.CounterRepository;
import com.ziryt.counter.exception.ExceedBottomLimitException;
import com.ziryt.counter.exception.ExceedIntegerException;
import com.ziryt.counter.exception.ExceedTopLimitException;
import com.ziryt.counter.exception.NotFoundException;
import com.ziryt.counter.exception.NotUniqueNameException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

;

@Service
@AllArgsConstructor
public class CounterService {

    private final CounterRepository counterRepository;
    private final Logger logger = LoggerFactory.getLogger(CounterController.class);

    private Counter findCounterById(Integer id) throws NotFoundException{
        logger.trace("find counter by id={}", id);
        return counterRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Counter with id=" + id + " doesn't exist", id));
    }

    public List<Counter> getCounters(){
        logger.trace("getCounters method called");
        return counterRepository.findAll();
    }

    public Counter getCounter(Integer id){
        logger.trace("getCounter method called");
        return findCounterById(id);
    }

    public Counter createCounter(CreateCounterRequest request) {
        logger.trace("createCounter method called");
        Counter counter = new Counter();
        if (counterRepository.findByName(request.name()) != null){
            throw new NotUniqueNameException("Counter with provided name already exist");
        }
        counter.setName(request.name());
        counter.setInitialValue(request.initialValue());
        counter.setCurrentValue(request.initialValue());
        counter.setTopLimit(request.topLimit());
        counter.setBottomLimit(request.bottomLimit());
        counter.setColor(request.color());
        return counterRepository.save(counter);
    }

    public Counter incrementValue(Integer id){
        logger.trace("incrementValue method called");
        Counter counter = findCounterById(id);
        Integer currentValue = counter.getCurrentValue();
        if (currentValue >= Integer.MAX_VALUE) {
            throw new ExceedIntegerException("Counter reached maximum possible integer value", currentValue, id);
        }
        Integer limit = counter.getTopLimit();
        if (limit != null && currentValue >= limit) {
            throw new ExceedTopLimitException("Counter can not be higher than top limit\n" +
                    "Top limit: " + limit, currentValue, id);
        } else {
            counter.setCurrentValue(++currentValue);
            return counterRepository.save(counter);
        }
    }

    public Counter decrementValue(Integer id){
        logger.trace("decrementValue method called");
        Counter counter = findCounterById(id);
        Integer currentValue = counter.getCurrentValue();
        if (currentValue <= Integer.MIN_VALUE) {
            throw new ExceedIntegerException("Counter reached minimum possible integer value", currentValue, id);
        }
        Integer limit = counter.getBottomLimit();
        if (limit != null && currentValue <= limit) {
            throw new ExceedBottomLimitException("Counter can not be lower than bottom limit\n" +
                    "Bottom limit: " + limit, currentValue, id);
        } else {
            counter.setCurrentValue(--currentValue);
            return counterRepository.save(counter);
        }
    }

    public Counter updateValueBy(Integer id, UpdateValueRequest request){
        logger.trace("updateValue method called");
        Counter counter = findCounterById(id);
        long currentValue = counter.getCurrentValue().longValue();
        long sum = currentValue + request.value().longValue();
        if (sum > Integer.MIN_VALUE && sum < Integer.MAX_VALUE) {
            counter.setCurrentValue(Long.valueOf(sum).intValue());
            return counterRepository.save(counter);
        } else {
            throw new ExceedIntegerException(currentValue + " + " + request.value() +
                    " is outside of Integer boundaries", sum, id);
        }
    }

    public Counter setValue(Integer id, UpdateValueRequest request) {
        logger.trace("setValue method called");
        Counter counter = findCounterById(id);
        counter.setCurrentValue(request.value());
        counterRepository.save(counter);
        return counter;
    }

    public Counter setTopLimit(Integer id, UpdateValueRequest request) {
        logger.trace("setTopLimit method called");
        Counter counter = findCounterById(id);
        counter.setTopLimit(request.value());
        counterRepository.save(counter);
        return counter;
    }

    public Counter setBottomLimit(Integer id, UpdateValueRequest request) {
        logger.trace("setBottomLimit method called");
        Counter counter = findCounterById(id);
        counter.setBottomLimit(request.value());
        counterRepository.save(counter);
        return counter;
    }

    public Counter updateCounter(Integer id, UpdateCounterRequest request) {
        logger.trace("updateCounter method called");
        Counter counter = findCounterById(id);
        if (request.name() != null) {
            counter.setName(request.name());
        }
        if (request.initialValue() != null) {
            counter.setInitialValue(request.initialValue());
        }
        if (request.currentValue() != null) {
            counter.setCurrentValue(request.currentValue());
        }
        if (request.topLimit() != null) {
            counter.setTopLimit(request.topLimit());
        }
        if (request.bottomLimit() != null) {
            counter.setBottomLimit(request.bottomLimit());
        }
        if (request.color() != null) {
            counter.setColor(request.color());
        }
        return counterRepository.save(counter);
    }

    public void deleteCounter(Integer id){
        logger.trace("deleteCounter method called");
        Counter counter = findCounterById(id);
        counterRepository.delete(counter);
    }
}
