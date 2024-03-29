package com.ziryt.counter.service;

import com.ziryt.counter.model.DTO.CreateCounterRequest;
import com.ziryt.counter.model.DTO.SetLimitsRequest;
import com.ziryt.counter.model.DTO.UpdateCounterRequest;
import com.ziryt.counter.model.DTO.UpdateValueRequest;
import com.ziryt.counter.model.entity.Counter;
import com.ziryt.counter.repository.CounterRepository;
import com.ziryt.counter.exception.ExceedBottomLimitException;
import com.ziryt.counter.exception.ExceedIntegerException;
import com.ziryt.counter.exception.ExceedTopLimitException;
import com.ziryt.counter.exception.NotFoundException;
import com.ziryt.counter.exception.NotUniqueNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ziryt.counter.model.mapper.Mapper.createCounterRequestToEntity;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final CounterRepository counterRepository;

    // is it ok to be public?
    Counter findCounterById(Integer id) throws NotFoundException{
        return counterRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Counter with id=" + id + " doesn't exist", id));
    }

    public Page<Counter> getCounters(int page, int size){
        PageRequest pages = PageRequest.of(page, size);
        return counterRepository.findAll(pages);
    }

    public Counter getCounter(Integer id){
        return findCounterById(id);
    }

    public Counter createCounter(CreateCounterRequest request) {
        Optional.ofNullable(counterRepository.findByName(request.name())).ifPresent(duplicate -> {
            throw new NotUniqueNameException("Counter with provided name already exist");
        });

        Counter counter = createCounterRequestToEntity(request);
        counter.setCurrentValue(request.initialValue());

        return counterRepository.save(counter);
    }

    private void checkIncrementPossible(int id, int currentValue){
        if (currentValue == Integer.MAX_VALUE) {
            throw new ExceedIntegerException("Counter reached maximum possible integer value", currentValue, id);
        }
    }

    private void checkTopLimit(int id, int value, Integer limit){
        if (limit != null && value > limit) {
            throw new ExceedTopLimitException(
                    "Counter can not be higher than top limit\n" +
                            "Top limit: " + limit, value, id);
        }
    }

    public Counter incrementValue(Integer id){
        Counter counter = findCounterById(id);
        Integer currentValue = counter.getCurrentValue();
        Integer limit = counter.getTopLimit();

        checkIncrementPossible(id, currentValue);
        checkTopLimit(id, ++currentValue, limit);

        counter.setCurrentValue(currentValue);
        return counterRepository.save(counter);
    }

    private void checkDecrementPossible(int id, int currentValue){
        if (currentValue == Integer.MIN_VALUE) {
            throw new ExceedIntegerException("Counter reached minimum possible integer value", currentValue, id);
        }
    }

    private void checkBottomLimit(int id, int value, Integer limit){
        if (limit != null && value < limit) {
            throw new ExceedBottomLimitException(
                    "Counter can not be lower than bottom limit\n" +
                            "Bottom limit: " + limit, value, id);
        }
    }

    public Counter decrementValue(Integer id){
        Counter counter = findCounterById(id);
        Integer currentValue = counter.getCurrentValue();
        Integer limit = counter.getBottomLimit();

        checkDecrementPossible(id, currentValue);
        checkBottomLimit(id, --currentValue, limit);

        counter.setCurrentValue(currentValue);
        return counterRepository.save(counter);
    }

    private void checkSumInBoundaries(int id, long currentValue, int value, long sum){
        if (sum < Integer.MIN_VALUE || sum > Integer.MAX_VALUE) {
            throw new ExceedIntegerException(currentValue + " + " + value +
                    " is outside of Integer boundaries", sum, id);
        }
    }

    public Counter updateValueBy(Integer id, UpdateValueRequest request){
        Counter counter = findCounterById(id);
        Integer currentValue = counter.getCurrentValue();
        long sum = currentValue.longValue() + request.value().longValue();

        checkSumInBoundaries(id, currentValue, request.value(), sum);
        if (request.value() < 0){
            checkBottomLimit(id, (int) sum, counter.getBottomLimit());
        }
        else {
            checkTopLimit(id, (int) sum, counter.getTopLimit());
        }
        counter.setCurrentValue((int) sum);
        return counterRepository.save(counter);
    }

    public Counter setValue(Integer id, UpdateValueRequest request) {
        Counter counter = findCounterById(id);
        counter.setCurrentValue(request.value());
        return counterRepository.save(counter);
    }

    public Counter setLimit(Integer id, SetLimitsRequest request) {
        Counter counter = findCounterById(id);

        Optional.ofNullable(request.topLimit()).ifPresent(counter::setTopLimit);
        Optional.ofNullable(request.bottomLimit()).ifPresent(counter::setBottomLimit);

        return counterRepository.save(counter);
    }

    public Counter updateCounter(Integer id, UpdateCounterRequest request) {
        Counter counter = findCounterById(id);

        Optional.ofNullable(request.name()).ifPresent(counter::setName);
        Optional.ofNullable(request.initialValue()).ifPresent(counter::setInitialValue);
        Optional.ofNullable(request.currentValue()).ifPresent(counter::setCurrentValue);
        Optional.ofNullable(request.topLimit()).ifPresent(counter::setTopLimit);
        Optional.ofNullable(request.bottomLimit()).ifPresent(counter::setBottomLimit);
        Optional.ofNullable(request.color()).ifPresent(counter::setColor);

        return counterRepository.save(counter);
    }

    public void deleteCounter(Integer id){
        Counter counter = findCounterById(id);
        counterRepository.delete(counter);
    }
}
