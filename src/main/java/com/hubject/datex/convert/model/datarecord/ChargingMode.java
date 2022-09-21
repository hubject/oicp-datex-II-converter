package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChargingMode {
    MODE_1("Mode_1"),
    MODE_2("Mode_2"),
    MODE_3("Mode_3"),
    MODE_4("Mode_4"),
    CHADEMO("CHAdeMO");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
