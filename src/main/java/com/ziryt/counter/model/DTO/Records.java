package com.ziryt.counter.model.DTO;

public class Records {

    public record CreateCounterRequest(
            String name,
            Integer initialValue,
            Integer topLimit,
            Integer bottomLimit,
            String color
    ){}

    public record UpdateCounterRequest(
            String name,
            Integer initialValue,
            Integer currentValue,
            Integer topLimit,
            Integer bottomLimit,
            String color
    ){}

    public record UpdateValueRequest(
            Integer value
    ){}
}
