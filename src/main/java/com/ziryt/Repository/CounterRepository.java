package com.ziryt.Repository;

import com.ziryt.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository
        extends JpaRepository<Counter, Integer> {
}
