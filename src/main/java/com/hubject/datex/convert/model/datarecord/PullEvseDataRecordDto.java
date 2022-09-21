package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class PullEvseDataRecordDto {

    @JsonProperty("deltaType")
    private DeltaType deltaType;
    @JsonProperty("lastUpdate")
    private ZonedDateTime lastUpdate;
    @JsonProperty("EvseID")
    private String evseId;
    @JsonProperty("ChargingPoolID")
    private String chargingPoolId;
    @JsonProperty("ChargingStationID")
    private String chargingStationId;
    private List<InfoTextDto> chargingStationNames;
    private String hardwareManufacturer;
    private String chargingStationImage;
    private String subOperatorName;
    private AddressIso19773Dto address;
    private GeoCoordinatesDto geoCoordinates;
    private List<Plug> plugs;
    private Boolean dynamicPowerLevel;
    private List<ChargingFacilityDto> chargingFacilities;
    private Boolean renewableEnergy;
    private List<EnergySourceDto> energySource;
    private EnvironmentalImpactDto environmentalImpact;
    private CalibrationLawDataAvailability calibrationLawDataAvailability;
    private List<AuthenticationMode> authenticationModes;
    private Integer maxCapacity;
    private List<PaymentOption> paymentOptions;
    private List<ValueAddedService> valueAddedServices;
    private Accessibility accessibility;
    private AccessibilityLocation accessibilityLocation;
    private String hotlinePhoneNumber;
    private List<InfoTextDto> additionalInfo;
    private List<InfoTextDto> chargingStationLocationReference;
    private GeoCoordinatesDto geoChargingPointEntrance;
    private Boolean isOpen24Hours;
    private List<OpeningTimesDto> openingTimes;
    @JsonProperty("HubOperatorID")
    private String hubOperatorId;
    @JsonProperty("ClearinghouseID")
    private String clearingHouseId;
    private Boolean isHubjectCompatible;
    private DynamicInfoAvailable dynamicInfoAvailable;
    @JsonProperty("OperatorID")
    private String operatorId;
    private String operatorName;
}
