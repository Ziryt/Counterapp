package com.ziryt.counter.model.DTO;

public record SetLimitsRequest(
        Integer topLimit,
        Integer bottomLimit
) {}
