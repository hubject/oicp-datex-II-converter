package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValueAddedService {
    RESERVATION("Reservation"),
    DYNAMIC_PRICING("DynamicPricing"),
    PARKING_SENSORS("ParkingSensors"),
    MAXIMUM_POWER_CHARGING("MaximumPowerCharging"),
    PREDICTIVE_CHARGE_POINT_USAGE("PredictiveChargePointUsage"),
    CHARGING_PLANS("ChargingPlans"),
    ROOF_PROVIDED("RoofProvided"),
    NONE("None");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
