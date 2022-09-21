package com.hubject.datex.convert.converters.status.station;


import com.hubject.datex.energyinfrastructure.generated.infrastructure.ElectricChargingPointStatus;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureStationStatus;
import com.hubject.datex.convert.model.status.EvseStatus;
import com.hubject.datex.convert.model.status.EvseStatusRecordDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StationConverterTest {

    @Test
    void shouldAssemblyRefillPointByConverter() {
        //given
        EvseStatusRecordDto evseStatusRecord = EvseStatusRecordDto.builder().evseStatus(EvseStatus.AVAILABLE).build();

        //when
        EnergyInfrastructureStationStatus station = StationConverter.createStation(evseStatusRecord);

        //then
        assertThat(station.getRefillPointStatus()).hasSize(1);
        assertThat(station.getRefillPointStatus().get(0)).isInstanceOf(ElectricChargingPointStatus.class);
    }

    @Test
    void shouldMapAvailability() {
        //given
        EvseStatusRecordDto evseStatusRecord = EvseStatusRecordDto.builder().evseStatus(EvseStatus.AVAILABLE).build();

        //when
        EnergyInfrastructureStationStatus station = StationConverter.createStation(evseStatusRecord);

        //then
        assertThat(station.isIsAvailable()).isTrue();
    }
}