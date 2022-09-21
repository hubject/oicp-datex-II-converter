package com.hubject.datex.convert.converters.status.station.refill;

import com.hubject.datex.energyinfrastructure.generated.facilities.UnknownRates;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.ElectricChargingPointStatus;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.ElectricEnergyMix;
import com.hubject.datex.convert.model.status.EvseStatus;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ElectricChargePointStatusConverter {

    public ElectricChargingPointStatus createElectricChargingPointStatus(EvseStatus evseStatus) {
        ElectricChargingPointStatus electricChargingPointStatus = new ElectricChargingPointStatus();

        electricChargingPointStatus.setStatus(StatusMapper.map(evseStatus));
        electricChargingPointStatus.getElectricEnergyMixOverride().add(createElectricEnergyMixWithUnknownRates());

        return electricChargingPointStatus;
    }

    private ElectricEnergyMix createElectricEnergyMixWithUnknownRates() {
        ElectricEnergyMix electricEnergyMix = new ElectricEnergyMix();
        electricEnergyMix.setRates(new UnknownRates());
        return electricEnergyMix;
    }
}
