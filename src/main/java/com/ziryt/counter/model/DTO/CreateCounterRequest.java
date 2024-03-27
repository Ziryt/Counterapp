package com.ziryt.counter.model.DTO;

import lombok.NonNull;

public record CreateCounterRequest(
        @NonNull
        String name,
        @NonNull
        Integer initialValue,
        Integer topLimit,
        Integer bottomLimit,
        String color
){}