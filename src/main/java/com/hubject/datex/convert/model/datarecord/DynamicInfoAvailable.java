package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DynamicInfoAvailable {
    TRUE("true"),
    FALSE("false"),
    AUTO("auto");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
