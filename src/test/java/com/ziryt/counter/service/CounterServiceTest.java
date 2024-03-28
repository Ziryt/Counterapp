package com.ziryt.counter.service;

import com.ziryt.counter.repository.CounterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CounterServiceTest {

    @Mock
    private CounterRepository repositoryMock;
    private CounterService serviceTest;

    @BeforeEach
    void setUp() {
        serviceTest = new CounterService(repositoryMock);
    }

    @Test
    void getCounters() {
        // given
        int page = 0;
        int size = 5;
        PageRequest pages = PageRequest.of(page,size);
        // when
        serviceTest.getCounters(page, size);

        // then
        verify(repositoryMock).findAll(pages);

    }

    @Test
    @Disabled
    void getCounter() {
    }

    @Test
    @Disabled
    void createCounter() {
    }

    @Test
    @Disabled
    void incrementValue() {
    }

    @Test
    @Disabled
    void decrementValue() {
    }

    @Test
    @Disabled
    void updateValueBy() {
    }

    @Test
    @Disabled
    void setValue() {
    }

    @Test
    @Disabled
    void setLimit() {
    }

    @Test
    @Disabled
    void updateCounter() {
    }

    @Test
    @Disabled
    void deleteCounter() {
    }
}