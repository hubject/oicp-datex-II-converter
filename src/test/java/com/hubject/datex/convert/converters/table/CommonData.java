package com.hubject.datex.convert.converters.table;

import com.hubject.datex.convert.model.datarecord.AddressIso19773Dto;
import com.hubject.datex.convert.model.datarecord.GeoCoordinatesDto;
import com.hubject.datex.convert.model.datarecord.GeoCoordinatesGoogleDto;
import com.hubject.datex.convert.model.datarecord.OpeningTimesDto;
import com.hubject.datex.convert.model.datarecord.PeriodDto;
import com.hubject.datex.convert.model.datarecord.PullEvseDataRecordDto;
import com.hubject.datex.convert.model.datarecord.Weekday;

import java.util.List;

public final class CommonData {

    public static final PullEvseDataRecordDto.PullEvseDataRecordDtoBuilder VALIDATED_RECORD_PART = PullEvseDataRecordDto.builder()
            .chargingFacilities(List.of())
            .plugs(List.of())
            .isOpen24Hours(false)
            .openingTimes(List.of(OpeningTimesDto.builder()
                    .on(Weekday.EVERYDAY)
                    .period(List.of(PeriodDto.builder()
                            .begin("09:00")
                            .end("18:00")
                            .build()))
                    .build()))
            .address(AddressIso19773Dto.builder().build())
            .authenticationModes(List.of())
            .geoCoordinates(GeoCoordinatesDto.builder()
                    .google(GeoCoordinatesGoogleDto.builder()
                            .coordinates("52.480495 13.356465")
                            .build())
                    .build());
}
