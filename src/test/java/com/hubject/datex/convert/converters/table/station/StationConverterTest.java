package com.hubject.datex.convert.converters.table.station;

import com.hubject.datex.convert.converters.table.CommonData;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.AuthenticationAndIdentificationEnum;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.ElectricChargingPoint;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureStation;
import com.hubject.datex.convert.model.datarecord.AuthenticationMode;
import com.hubject.datex.convert.model.datarecord.PullEvseDataRecordDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StationConverterTest {

    @Test
    public void shouldAssembleRefillPointByConverter() {
        //given
        PullEvseDataRecordDto pullEvseDataRecordDto = CommonData.VALIDATED_RECORD_PART.build();

        //when
        EnergyInfrastructureStation station = StationConverter.createStation(pullEvseDataRecordDto);

        //then
        assertThat(station.getRefillPoint().size()).isEqualTo(1);
        assertThat(station.getRefillPoint().get(0)).isInstanceOf(ElectricChargingPoint.class);
    }

    @Test
    public void shouldAddAllModesToStation() {
        //given
        PullEvseDataRecordDto pullEvseDataRecordDto = CommonData.VALIDATED_RECORD_PART
                .authenticationModes(List.of(AuthenticationMode.NFC_RFID_CLASSIC, AuthenticationMode.NFC_RFID_DESFIRE))
                .build();

        //when
        EnergyInfrastructureStation station = StationConverter.createStation(pullEvseDataRecordDto);
        List<AuthenticationAndIdentificationEnum> methods = station.getAuthenticationAndIdentificationMethods();

        //then
        assertThat(methods.size()).isEqualTo(2);
        assertThat(methods.get(0)).isNotEqualTo(methods.get(1));
    }
}