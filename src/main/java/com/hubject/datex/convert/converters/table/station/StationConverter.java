package com.hubject.datex.convert.converters.table.station;


import com.hubject.datex.convert.converters.table.station.refill.ElectricChargePointConverter;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.AuthenticationAndIdentificationEnum;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureStation;
import com.hubject.datex.convert.model.datarecord.AuthenticationMode;
import com.hubject.datex.convert.model.datarecord.PullEvseDataRecordDto;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class StationConverter {

    public EnergyInfrastructureStation createStation(PullEvseDataRecordDto record) {
        EnergyInfrastructureStation station = new EnergyInfrastructureStation();

        station.getAuthenticationAndIdentificationMethods().addAll(getAuthenticationsAndIdentifications(record.getAuthenticationModes()));
        station.getRefillPoint().add(ElectricChargePointConverter.createElectricChargingPoint(record));

        return station;
    }

    private List<AuthenticationAndIdentificationEnum> getAuthenticationsAndIdentifications(List<AuthenticationMode> modes) {
        return modes.stream().map(AuthenticationMapper::map).collect(Collectors.toList());
    }
}
