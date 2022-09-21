package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class AddressIso19773Dto {

    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String houseNum;
    private String floor;
    private String region;
    private Boolean parkingFacility;
    private String parkingSpot;
    private String timeZone;
}
