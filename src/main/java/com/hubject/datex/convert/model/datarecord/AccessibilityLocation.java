package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccessibilityLocation {
    ON_STREET("OnStreet"),
    PARKING_LOT("ParkingLot"),
    PARKING_GARAGE("ParkingGarage"),
    UNDERGROUND_PARKING_GARAGE("UndergroundParkingGarage");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
