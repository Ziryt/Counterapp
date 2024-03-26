package com.ziryt.counter.repository;

import com.ziryt.counter.model.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository
        extends JpaRepository<Counter, Integer> {

    Counter findByName(String name);
}
