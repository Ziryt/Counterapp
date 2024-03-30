package com.ziryt.counter.service;

import com.ziryt.counter.exception.ExceedBottomLimitException;
import com.ziryt.counter.exception.ExceedIntegerException;
import com.ziryt.counter.exception.ExceedTopLimitException;
import com.ziryt.counter.exception.NotFoundException;

import com.ziryt.counter.exception.NotUniqueNameException;
import com.ziryt.counter.model.DTO.CreateCounterRequest;
import com.ziryt.counter.model.DTO.SetLimitsRequest;
import com.ziryt.counter.model.DTO.UpdateCounterRequest;
import com.ziryt.counter.model.DTO.UpdateValueRequest;
import com.ziryt.counter.model.entity.Counter;
import com.ziryt.counter.repository.CounterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void shouldGetCounters() {
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
    void shouldGetCounter() {
        // given
        int expected = 1;
        Counter counterMock = Counter
                .builder()
                .Id(expected)
                .build();

        // when
        when(repositoryMock.findById(expected)).thenReturn(Optional.ofNullable(counterMock));
        serviceTest.getCounter(expected);

        // then
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        verify(repositoryMock).findById(captor.capture());

        int actual = captor.getValue();

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void throwsWhenCounterNotFound() {
        // given
        int expected = 1;

        // when
        // then
        assertThatThrownBy(() -> serviceTest.getCounter(expected))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Counter with id=" + expected + " doesn't exist");

    }

    @Test
    void shouldCreateCounter() {
        // given
        CreateCounterRequest request = new CreateCounterRequest(
                "TestName",
                35,
                40,
                20,
                "green"
        );
        Counter expected = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(35)
                .topLimit(40)
                .bottomLimit(20)
                .color("green")
                .build();

        // when
        serviceTest.createCounter(request);

        // then
        ArgumentCaptor<Counter> captor = ArgumentCaptor.forClass(Counter.class);
        verify(repositoryMock).save(captor.capture());

        Counter actual = captor.getValue();

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void throwsWhenCreateWithNotUniqueName() {
        // given
        CreateCounterRequest request = new CreateCounterRequest(
                "TestName",
                35,
                40,
                20,
                "green"
        );
        Counter nonUniqueName = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(35)
                .topLimit(40)
                .bottomLimit(20)
                .color("green")
                .build();

        // when
        when(repositoryMock.findByName(request.name())).thenReturn(nonUniqueName);

        // then
        assertThatThrownBy(() -> serviceTest.createCounter(request))
                .isInstanceOf(NotUniqueNameException.class)
                .hasMessageContaining("Counter with provided name already exist");
        verify(repositoryMock, never()).save(any());

    }


    @Test
    void shouldIncrementValue() {
        // given
        int id = 1;
        Counter counter = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(35)
                .topLimit(40)
                .bottomLimit(20)
                .color("green")
                .build();
        Counter expected = counter.toBuilder().currentValue(counter.getCurrentValue() + 1).build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));
        serviceTest.incrementValue(id);

        // then
        ArgumentCaptor<Counter> captor = ArgumentCaptor.forClass(Counter.class);
        verify(repositoryMock).save(captor.capture());

        Counter actual = captor.getValue();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void throwsWhenExceedIntegerMaximum() {
        // given
        int id = 1;
        Counter counter = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(Integer.MAX_VALUE)
                .topLimit(40)
                .bottomLimit(20)
                .color("green")
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));

        // then
        assertThatThrownBy(() -> serviceTest.
                incrementValue(id))
                .isInstanceOf(ExceedIntegerException.class)
                .hasMessageContaining("Counter reached maximum possible integer value");
        verify(repositoryMock, never()).save(any());

    }

    @Test
    void throwsWhenExceedTopLimit() {
        // given
        int id = 1;
        Counter counter = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(40)
                .topLimit(40)
                .bottomLimit(-40)
                .color("green")
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));

        // then
        assertThatThrownBy(() -> serviceTest.incrementValue(id))
                .isInstanceOf(ExceedTopLimitException.class)
                .hasMessageContaining("Counter can not be higher than top limit\n" +
                        "Top limit: ");
        verify(repositoryMock, never()).save(any());

    }

    @Test
    void shouldDecrementValue() {
        // given
        int id = 1;
        Counter counter = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(35)
                .topLimit(40)
                .bottomLimit(-40)
                .color("green")
                .build();
        Counter expected = counter.toBuilder()
                .currentValue(counter.getCurrentValue() - 1)
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));
        serviceTest.decrementValue(id);

        // then
        ArgumentCaptor<Counter> captor = ArgumentCaptor.forClass(Counter.class);
        verify(repositoryMock).save(captor.capture());

        Counter actual = captor.getValue();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void throwsWhenExceedIntegerMinimum() {
        // given
        int id = 1;
        Counter counter = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(Integer.MIN_VALUE)
                .topLimit(40)
                .bottomLimit(-40)
                .color("green")
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));

        // then
        assertThatThrownBy(() -> serviceTest.
                decrementValue(id))
                .isInstanceOf(ExceedIntegerException.class)
                .hasMessageContaining("Counter reached minimum possible integer value");
        verify(repositoryMock, never()).save(any());

    }

    @Test
    void throwsWhenExceedBottomLimit() {
        // given
        int id = 1;
        Counter counter = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(-40)
                .topLimit(40)
                .bottomLimit(-40)
                .color("green")
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));

        // then
        assertThatThrownBy(() -> serviceTest.decrementValue(id))
                .isInstanceOf(ExceedBottomLimitException.class)
                .hasMessageContaining("Counter can not be lower than bottom limit\n" +
                        "Bottom limit: ");
        verify(repositoryMock, never()).save(any());

    }

    @Test
    void shouldUpdateValueUpBy() {
        // given
        int id = 1;
        UpdateValueRequest request = new UpdateValueRequest(12);
        Counter counter = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(4)
                .topLimit(40)
                .bottomLimit(-40)
                .color("green")
                .build();
        Counter expected = counter.toBuilder()
                .currentValue(counter.getCurrentValue() + request.value())
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));
        serviceTest.updateValueBy(id, request);

        // then
        ArgumentCaptor<Counter> captor = ArgumentCaptor.forClass(Counter.class);
        verify(repositoryMock).save(captor.capture());

        Counter actual = captor.getValue();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldUpdateValueDownBy() {
        // given
        int id = 1;
        UpdateValueRequest request = new UpdateValueRequest(-12);
        Counter counter = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(4)
                .topLimit(40)
                .bottomLimit(-40)
                .color("green")
                .build();
        Counter expected = counter.toBuilder()
                .currentValue(counter.getCurrentValue() + request.value())
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));
        serviceTest.updateValueBy(id, request);

        // then
        ArgumentCaptor<Counter> captor = ArgumentCaptor.forClass(Counter.class);
        verify(repositoryMock).save(captor.capture());

        Counter actual = captor.getValue();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void throwsWhenSumOutOfBoundaries() {
        // given
        int id = 1;
        UpdateValueRequest request = new UpdateValueRequest(-1500000000);
        Counter counter = Counter.builder()
                .Id(id)
                .name("TestName")
                .initialValue(35)
                .currentValue(-1500000000)
                .topLimit(40)
                .bottomLimit(-40)
                .color("green")
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));

        // then
        assertThatThrownBy(() -> serviceTest.updateValueBy(id, request))
                .isInstanceOf(ExceedIntegerException.class)
                .hasMessageContaining(counter.getCurrentValue() + " + " + request.value() +
                        " is outside of Integer boundaries");
        verify(repositoryMock, never()).save(any());
    }

    @Test
    void shouldSetValue() {
        // given
        int id = 1;
        UpdateValueRequest request = new UpdateValueRequest(-12);
        Counter counter = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(35)
                .topLimit(40)
                .bottomLimit(-40)
                .color("green")
                .build();
        Counter expected = counter.toBuilder()
                .currentValue(request.value())
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));
        serviceTest.setValue(id, request);

        // then
        ArgumentCaptor<Counter> captor = ArgumentCaptor.forClass(Counter.class);
        verify(repositoryMock).save(captor.capture());

        Counter actual = captor.getValue();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldSetLimit() {
        // given
        int id = 1;
        SetLimitsRequest request = new SetLimitsRequest(-30, 90);
        Counter counter = Counter.builder()
                .name("TestName")
                .initialValue(35)
                .currentValue(35)
                .topLimit(40)
                .bottomLimit(-40)
                .color("green")
                .build();
        Counter expected = counter.toBuilder()
                .topLimit(request.topLimit())
                .bottomLimit(request.bottomLimit())
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));
        serviceTest.setLimit(id, request);

        // then
        ArgumentCaptor<Counter> captor = ArgumentCaptor.forClass(Counter.class);
        verify(repositoryMock).save(captor.capture());

        Counter actual = captor.getValue();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldUpdateCounter() {
        // given
        int id = 1;
        UpdateCounterRequest request = new UpdateCounterRequest(
                "AnotherName",
                10,
                -23,
                200,
                -344,
                "purple");
        Counter counter = Counter.builder()
                .Id(id)
                .name("TestName")
                .initialValue(35)
                .currentValue(35)
                .topLimit(40)
                .bottomLimit(-40)
                .color("green")
                .build();
        Counter expected = counter.toBuilder()
                .name(request.name())
                .initialValue(request.initialValue())
                .currentValue(request.currentValue())
                .topLimit(request.topLimit())
                .bottomLimit(request.bottomLimit())
                .color(request.color())
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));
        serviceTest.updateCounter(id, request);

        // then
        ArgumentCaptor<Counter> captor = ArgumentCaptor.forClass(Counter.class);
        verify(repositoryMock).save(captor.capture());

        Counter actual = captor.getValue();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldDeleteCounter() {
        // given
        int id = 1;
        Counter counter = Counter.builder()
                .Id(id)
                .name("TestName")
                .initialValue(35)
                .currentValue(35)
                .topLimit(40)
                .bottomLimit(-40)
                .color("green")
                .build();

        // when
        when(repositoryMock.findById(id)).thenReturn(Optional.of(counter));
        serviceTest.deleteCounter(id);

        // then
        verify(repositoryMock).delete(counter);

    }
}