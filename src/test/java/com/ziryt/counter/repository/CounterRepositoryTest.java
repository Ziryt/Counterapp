package com.ziryt.counter.repository;

import com.ziryt.counter.model.entity.Counter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CounterRepositoryTest {

    @Autowired
    private CounterRepository repositoryTest;

    @AfterEach
    void tearDown() {
        repositoryTest.deleteAll();
    }

    @Test
    void itShouldFindByName() {
        // given
        Counter expected = Counter.builder()
                .name("TestName")
                .initialValue(40)
                .currentValue(21)
                .color("green")
                .build();
        repositoryTest.save(expected);

        // when
        Counter actual = repositoryTest.findByName("TestName");

        // then
        assertThat(actual.getName()).isEqualTo(expected.getName());

    }

    @Test
    void itShouldReturnNull() {
        // given

        // when
        Counter actual = repositoryTest.findByName("TestName");

        // then
        assertThat(actual).isNull();

    }
}