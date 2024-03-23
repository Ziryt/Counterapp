package com.ziryt.counter;

import com.ziryt.DTO.Counter;
import com.ziryt.DTO.Records.CreateCounterRequest;
import com.ziryt.DTO.Records.UpdateCounterRequest;
import com.ziryt.DTO.Records.UpdateValueRequest;
import com.ziryt.exeption.ExceedLimitException;
import com.ziryt.exeption.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

;

@Service
@AllArgsConstructor
public class CounterService {

    private final CounterRepository counterRepository;

    private Counter findCounterById(Integer id) throws NotFoundException{
        return counterRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Counter with id=" + id + " doesn't exist"));
    }

    public List<Counter> getCounters(){
        return counterRepository.findAll();
    }

    public Counter getCounter(Integer id){
        return findCounterById(id);
    }

    public Counter createCounter(CreateCounterRequest request) {
        Counter counter = new Counter();
        counter.setName(request.name());
        counter.setInitialValue(request.initialValue());
        counter.setCurrentValue(request.initialValue());
        counter.setTopLimit(request.topLimit());
        counter.setBottomLimit(request.bottomLimit());
        counter.setColor(request.color());
        return counterRepository.save(counter);
    }

    public Counter incrementValue(Integer id){
        Counter counter = findCounterById(id);
        Integer currentValue = counter.getCurrentValue();
        if (currentValue >= Integer.MAX_VALUE) {
            throw new ExceedLimitException("Counter reached maximum possible integer value");
        }
        Integer limit = counter.getTopLimit();
        if (limit != null && currentValue >= limit) {
            throw new ExceedLimitException("Counter can not be higher than top limit\n" +
                    "Top limit: " + limit);
        } else {
            counter.setCurrentValue(++currentValue);
            return counterRepository.save(counter);
        }
    }

    public Counter decrementValue(Integer id){
        Counter counter = findCounterById(id);
        Integer currentValue = counter.getCurrentValue();
        if (currentValue <= Integer.MIN_VALUE) {
            throw new ExceedLimitException("Counter reached minimum possible integer value");
        }
        Integer limit = counter.getBottomLimit();
        if (limit != null && currentValue <= limit) {
            throw new ExceedLimitException("Counter can not be lower than bottom limit\n" +
                    "Bottom limit: " + limit);
        } else {
            counter.setCurrentValue(--currentValue);
            return counterRepository.save(counter);
        }
    }

    public Counter updateValue(Integer id, UpdateValueRequest request){
        Counter counter = findCounterById(id);
        long currentValue = counter.getCurrentValue().longValue();
        long sum = currentValue + request.value().longValue();
        if (sum > Integer.MIN_VALUE && sum < Integer.MAX_VALUE) {
            counter.setCurrentValue(Long.valueOf(sum).intValue());
            return counterRepository.save(counter);
        } else {
            throw new ExceedLimitException(currentValue + " + " + request.value() +
                    " is outside of Integer boundaries");
        }
    }

    public Counter setValue(Integer id, UpdateValueRequest request) {
        Counter counter = findCounterById(id);
        counter.setCurrentValue(request.value());
        counterRepository.save(counter);
        return counter;
    }

    public Counter setTopLimit(Integer id, UpdateValueRequest request) {
        Counter counter = findCounterById(id);
        counter.setTopLimit(request.value());
        counterRepository.save(counter);
        return counter;
    }

    public Counter setBottomLimit(Integer id, UpdateValueRequest request) {
        Counter counter = findCounterById(id);
        counter.setBottomLimit(request.value());
        counterRepository.save(counter);
        return counter;
    }

    public Counter updateCounter(Integer id, UpdateCounterRequest request) {
        Counter counter = findCounterById(id);
        if (request.name() != null) {
            counter.setName(request.name());
        }
        if (request.initialValue() != null) {
            counter.setInitialValue(request.initialValue());
        }
        if (request.initialValue() != null) {
            counter.setCurrentValue(request.currentValue());
        }
        if (request.initialValue() != null) {
            counter.setTopLimit(request.topLimit());
        }
        if (request.initialValue() != null) {
            counter.setBottomLimit(request.bottomLimit());
        }
        if (request.color() != null) {
            counter.setColor(request.color());
        }
        return counterRepository.save(counter);
    }

    public void deleteCounter(Integer id){
        Counter counter = findCounterById(id);
        counterRepository.delete(counter);
    }
}
