package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeltaType {
    UPDATE("update"),
    INSERT("insert"),
    DELETE("delete");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
