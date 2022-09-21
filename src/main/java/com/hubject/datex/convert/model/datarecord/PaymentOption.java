package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentOption {
    NO_PAYMENT("No Payment"),
    DIRECT("Direct"),
    CONTRACT("Contract");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
