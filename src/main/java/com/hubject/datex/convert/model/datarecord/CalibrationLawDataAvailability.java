package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CalibrationLawDataAvailability {
    LOCAL("Local"),
    EXTERNAL("External"),
    NOT_AVAILABLE("Not Available");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
