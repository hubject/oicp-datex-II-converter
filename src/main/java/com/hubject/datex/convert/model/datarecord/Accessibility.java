package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Accessibility {
    FREE_PUBLICLY_ACCESSIBLE("Free publicly accessible"),
    RESTRICTED_ACCESS("Restricted access"),
    PAYING_PUBLICLY_ACCESSIBLE("Paying publicly accessible"),
    TEST_STATION("Test Station");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
