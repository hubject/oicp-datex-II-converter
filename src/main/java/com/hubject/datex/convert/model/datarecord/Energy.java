package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Energy {
    SOLAR("Solar"),
    WIND("Wind"),
    HYDRO_POWER("HydroPower"),
    GEOTHERMAL_ENERGY("GeothermalEnergy"),
    BIOMASS("Biomass"),
    COAL("Coal"),
    NUCLEAR_ENERGY("NuclearEnergy"),
    PETROLEUM("Petroleum"),
    NATURAL_GAS("NaturalGas");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
