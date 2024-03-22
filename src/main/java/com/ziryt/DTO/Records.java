package com.ziryt.DTO;

public class Records {

    public record NewCounterRequest(
            String name,
            Integer initialValue,
            Integer topLimit,
            Integer bottomLimit,
            String color
    ){}

    public record CounterRequest(
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
