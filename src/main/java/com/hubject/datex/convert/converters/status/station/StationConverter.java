package com.hubject.datex.convert.converters.status.station;

import com.hubject.datex.convert.converters.status.station.refill.ElectricChargePointStatusConverter;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureStationStatus;
import com.hubject.datex.convert.model.status.EvseStatusRecordDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StationConverter {

    public EnergyInfrastructureStationStatus createStation(EvseStatusRecordDto evseStatus) {
        EnergyInfrastructureStationStatus stationStatus = new EnergyInfrastructureStationStatus();

        stationStatus.setIsAvailable(AvailabilityMapper.map(evseStatus.getEvseStatus()));
        stationStatus.getRefillPointStatus().add(ElectricChargePointStatusConverter.createElectricChargingPointStatus(evseStatus.getEvseStatus()));

        return stationStatus;
    }
}
