package com.ziryt.counter;

import com.ziryt.DTO.Counter;
import com.ziryt.exeption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ziryt.DTO.Records.*;

import java.util.List;

@Service
public class CounterService {

    private final CounterRepository counterRepository;

    @Autowired
    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

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

    public void addCounter(NewCounterRequest request){
        Counter counter = new Counter();
        counter.setName(request.name());
        counter.setInitialValue(request.initialValue());
        counter.setCurrentValue(request.initialValue());
        counter.setTopLimit(request.topLimit());
        counter.setTopLimit(request.bottomLimit());
        counter.setColor(request.color());
        counterRepository.save(counter);
    }

    public Counter incrementValue(Integer id){
        Counter counter = findCounterById(id);
        Integer currentValue = counter.getCurrentValue();
        counter.setCurrentValue(currentValue++);
        counterRepository.save(counter);
        return counter;
    }

    public Counter decrementValue(Integer id){
        Counter counter = findCounterById(id);
        Integer currentValue = counter.getCurrentValue();
        counter.setCurrentValue(currentValue--);
        counterRepository.save(counter);
        return counter;
    }

    public Counter updateValue(Integer id, UpdateValueRequest request){
        Counter counter = findCounterById(id);
        counter.setCurrentValue(counter.getCurrentValue() + request.value());
        counterRepository.save(counter);
        return counter;
    }

    public void updateCounter(Integer id, CounterRequest request){
        Counter counter = findCounterById(id);
        counter.setName(request.name());
        counter.setInitialValue(request.initialValue());
        counter.setCurrentValue(request.currentValue());
        counter.setTopLimit(request.topLimit());
        counter.setTopLimit(request.bottomLimit());
        counter.setColor(request.color());
        counterRepository.save(counter);
    }

    public void deleteCounter(Integer id){
        Counter counter = findCounterById(id);
        counterRepository.delete(counter);
    }
}
