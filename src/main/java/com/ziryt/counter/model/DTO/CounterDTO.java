package com.ziryt.counter.model.DTO;

public record CounterDTO (
        Integer Id,
        String name,
        Integer initialValue,
        Integer currentValue,
        Integer topLimit,
        Integer bottomLimit,
        String color

) {}