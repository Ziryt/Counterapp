package com.ziryt.counter;

import com.ziryt.counter.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository
        extends JpaRepository<Counter, Integer> {
}