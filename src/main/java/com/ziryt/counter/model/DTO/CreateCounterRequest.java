package com.ziryt.counter.model.DTO;

public record CreateCounterRequest(
        String name,
        Integer initialValue,
        Integer topLimit,
        Integer bottomLimit,
        String color
){}