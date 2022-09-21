package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class EnvironmentalImpactDto {

    @JsonProperty("CO2Emission")
    private BigDecimal co2Emission;

    @JsonProperty("NuclearWaste")
    private BigDecimal nuclearWaste;
}
