package com.ziryt.counter.model.DTO;

import lombok.NonNull;

public record UpdateValueRequest(
        @NonNull
        Integer value
){}