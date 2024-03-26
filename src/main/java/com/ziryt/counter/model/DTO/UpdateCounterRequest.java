package com.ziryt.counter.model.DTO;

public record UpdateCounterRequest(
        String name,
        Integer initialValue,
        Integer currentValue,
        Integer topLimit,
        Integer bottomLimit,
        String color
){}