package com.hubject.datex.convert.converters.status.station.refill;


import com.hubject.datex.energyinfrastructure.generated.facilities.UnknownRates;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.ElectricChargingPointStatus;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.ElectricEnergyMix;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.RefillPointStatusEnum;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.RefillPointStatusEnum2;
import com.hubject.datex.convert.model.status.EvseStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ElectricChargePointStatusConverterTest {

    @Test
    void shouldCreateElectricChargingPointStatus() {
        //given
        EvseStatus evseStatus = EvseStatus.AVAILABLE;
        ElectricChargingPointStatus expected = createExpectedChargingPoint();

        //when
        ElectricChargingPointStatus electricChargingPointStatus = ElectricChargePointStatusConverter.createElectricChargingPointStatus(evseStatus);

        //then
        assertThat(electricChargingPointStatus).usingRecursiveComparison().isEqualTo(expected);
    }

    private ElectricChargingPointStatus createExpectedChargingPoint() {
        RefillPointStatusEnum refillPointStatusEnum = new RefillPointStatusEnum();
        refillPointStatusEnum.setValue(RefillPointStatusEnum2.AVAILABLE);

        ElectricEnergyMix electricEnergyMix = new ElectricEnergyMix();
        electricEnergyMix.setRates(new UnknownRates());

        ElectricChargingPointStatus expected = new ElectricChargingPointStatus();
        expected.setStatus(refillPointStatusEnum);
        expected.getElectricEnergyMixOverride().add(electricEnergyMix);
        return expected;
    }
}