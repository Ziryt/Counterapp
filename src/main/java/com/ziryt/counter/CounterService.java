package com.ziryt.counter;

import org.springframework.stereotype.Service;
import com.ziryt.counter.CounterController.NewCounterRequest;

import java.util.List;

@Service
public class CounterService {

    private final CounterRepository counterRepository;


    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    public List<Counter> getCounters(){
        return counterRepository.findAll();
    }

    public String getCounter(Integer id){
        return counterRepository.findById(id).toString();
    }

    public void addCounter(NewCounterRequest request){
        Counter counter = new Counter();
        counter.setName(request.name());
        counter.setCount(request.initial_value());
        counter.setLimited(request.limit());
        counter.setColor(request.color());
        counterRepository.save(counter);
    }
    public void updateCounter(Integer id, NewCounterRequest request){
        Counter counter = new Counter();
        counter.setId(id);
        counter.setName(request.name());
        counter.setCount(request.initial_value());
        counter.setLimited(request.limit());
        counter.setColor(request.color());
        counterRepository.save(counter);
    }

    public void deleteCounter(Integer id){
        counterRepository.deleteById(id);
    }
}
