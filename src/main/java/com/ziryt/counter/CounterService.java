package com.ziryt.counter;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ziryt.counter.CounterController.NewCounterRequest;
import com.ziryt.counter.CounterController.CounterRequest;
import com.ziryt.counter.CounterController.UpdateValueRequest;

import java.util.List;
import java.util.Optional;

@Service
public class CounterService {

    private final CounterRepository counterRepository;

    @Autowired
    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    public List<Counter> getCounters(){
        return counterRepository.findAll();
    }

    public Counter getCounter(Integer id){
        Optional<Counter> optionalCounter = counterRepository.findById(id);
        if (optionalCounter.isEmpty()){
           throw new EntityNotFoundException("Counter with id:" + id + " doesn't exist");
        }
        return optionalCounter.get();
    }

    public void addCounter(NewCounterRequest request){
        Counter counter = new Counter();
        counter.setName(request.name());
        counter.setInitial_value(request.initial_value());
        counter.setCurrent_value(request.initial_value());
        counter.setLimited(request.limit());
        counter.setColor(request.color());
        counterRepository.save(counter);
    }

    public Counter updateValue(Integer id, UpdateValueRequest request, Boolean inc){
        Optional<Counter> optionalCounter = counterRepository.findById(id);
        if (optionalCounter.isEmpty()){
            throw new EntityNotFoundException("Counter with id:" + id + " doesn't exist");
        }
        Counter counter = optionalCounter.get();
        if (inc) {
            counter.setCurrent_value(counter.getCurrent_value() + request.value());
        } else {
            counter.setCurrent_value(counter.getCurrent_value() - request.value());
        }
        counterRepository.save(counter);
        return counter;
    }

    public void updateCounter(Integer id, CounterRequest request){
        Optional<Counter> optionalCounter = counterRepository.findById(id);
        if (optionalCounter.isEmpty()){
            throw new EntityNotFoundException("Counter with id:" + id + " doesn't exist");
        }
        Counter counter = optionalCounter.get();
        counter.setName(request.name());
        counter.setInitial_value(request.initial_value());
        counter.setCurrent_value(request.current_value());
        counter.setLimited(request.limit());
        counter.setColor(request.color());
        counterRepository.save(counter);
    }

    public void deleteCounter(Integer id){
        counterRepository.deleteById(id);
    }
}
