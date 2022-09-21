package com.hubject.datex.convert.model.status;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EvseStatus {
    AVAILABLE("Available"),
    RESERVED("Reserved"),
    OCCUPIED("Occupied"),
    OUT_OF_SERVICE("OutOfService"),
    EVSE_NOT_FOUND("EvseNotFound"),
    UNKNOWN("Unknown");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
