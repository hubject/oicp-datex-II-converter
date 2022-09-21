package com.hubject.datex.convert.converters.table.station.refill;

import com.hubject.datex.convert.converters.table.CommonData;
import com.hubject.datex.energyinfrastructure.generated.facilities.ContactInformation;
import com.hubject.datex.energyinfrastructure.generated.facilities.OperatingHoursSpecification;
import com.hubject.datex.energyinfrastructure.generated.facilities.OrganisationSpecification;
import com.hubject.datex.energyinfrastructure.generated.facilities.OrganisationUnit;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.ElectricChargingPoint;
import com.hubject.datex.energyinfrastructure.generated.location_referencing.PointLocation;
import com.hubject.datex.convert.model.datarecord.ChargingFacilityDto;
import com.hubject.datex.convert.model.datarecord.Plug;
import com.hubject.datex.convert.model.datarecord.PullEvseDataRecordDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ElectricChargePointConverterTest {

    @Test
    void shouldAddMultipleConnectorsToChargePointIfPlugsAreMoreThanOne() {
        //given
        PullEvseDataRecordDto pullEvseDataRecordDto = CommonData.VALIDATED_RECORD_PART
                .plugs(List.of(Plug.TESLA_CONNECTOR, Plug.AVCON_CONNECTOR))
                .build();

        //when
        ElectricChargingPoint refillPoint = ElectricChargePointConverter.createElectricChargingPoint(pullEvseDataRecordDto);

        //then
        assertThat(refillPoint.getConnector().size()).isEqualTo(2);
        assertThat(refillPoint.getConnector().get(0).getConnectorType().getValue()).isEqualTo(ConnectorTypeEnum2.TESLA_CONNECTOR_EUROPE);
        assertThat(refillPoint.getConnector().get(1).getConnectorType().getValue()).isEqualTo(ConnectorTypeEnum2.OTHER);
    }

    @Test
    void shouldAddVoltageAndPower() {
        //given
        PullEvseDataRecordDto pullEvseDataRecordDto = CommonData.VALIDATED_RECORD_PART
                .chargingFacilities(List.of(
                        ChargingFacilityDto.builder()
                                .power(10d)
                                .voltage(20d)
                                .build(),
                        ChargingFacilityDto.builder()
                                .power(12d)
                                .voltage(22d)
                                .build()))
                .build();

        List<Float> expectedPowers = List.of(10f, 12f);
        List<Float> expectedVoltages = List.of(20f, 22f);

        //when
        ElectricChargingPoint electricChargingPoint = ElectricChargePointConverter.createElectricChargingPoint(pullEvseDataRecordDto);

        //then
        assertThat(electricChargingPoint.getAvailableChargingPower()).hasSameElementsAs(expectedPowers);
        assertThat(electricChargingPoint.getAvailableVoltage()).hasSameElementsAs(expectedVoltages);
    }

    @Test
    void shouldAddOnlyPower() {
        //given
        PullEvseDataRecordDto pullEvseDataRecordDto = CommonData.VALIDATED_RECORD_PART
                .chargingFacilities(List.of(ChargingFacilityDto.builder().power(10d).build()))
                .build();

        //when
        ElectricChargingPoint electricChargingPoint = ElectricChargePointConverter.createElectricChargingPoint(pullEvseDataRecordDto);

        //then
        assertThat(electricChargingPoint.getAvailableChargingPower()).hasSameElementsAs(List.of(10f));
        assertThat(electricChargingPoint.getAvailableVoltage()).isEmpty();
    }

    @Test
    void shouldAddEvseId() {
        //given
        String id = "SE*CLE*E18346*2";
        PullEvseDataRecordDto pullEvseDataRecordDto = CommonData.VALIDATED_RECORD_PART.evseId(id).build();

        //when
        ElectricChargingPoint electricChargingPoint = ElectricChargePointConverter.createElectricChargingPoint(pullEvseDataRecordDto);

        //then
        assertThat(electricChargingPoint.getId()).isEqualTo(id);
    }

    @Test
    void shouldAddOrganisationWithEmptyContactInformation() {
        //given
        PullEvseDataRecordDto pullEvseDataRecordDto = CommonData.VALIDATED_RECORD_PART.build();
        OrganisationSpecification expected = createOrganisationWithEmptyContactInformation();

        //when
        ElectricChargingPoint electricChargingPoint = ElectricChargePointConverter.createElectricChargingPoint(pullEvseDataRecordDto);

        //then
        assertThat(electricChargingPoint.getOperator()).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void shouldAssembleFieldsByConverters() {
        //given
        PullEvseDataRecordDto pullEvseDataRecordDto = CommonData.VALIDATED_RECORD_PART.build();

        //when
        ElectricChargingPoint electricChargingPoint = ElectricChargePointConverter.createElectricChargingPoint(pullEvseDataRecordDto);

        //then
        assertThat(electricChargingPoint.getOperatingHours()).isInstanceOf(OperatingHoursSpecification.class);
        assertThat(electricChargingPoint.getLocationReference()).isInstanceOf(PointLocation.class);
    }

    private OrganisationSpecification createOrganisationWithEmptyContactInformation() {
        OrganisationSpecification organisationSpecification = new OrganisationSpecification();
        OrganisationUnit organisationUnit = new OrganisationUnit();
        ContactInformation contactInformation = new ContactInformation();

        organisationUnit.getContactInformation().add(contactInformation);
        organisationSpecification.getOrganisationUnit().add(organisationUnit);
        return organisationSpecification;
    }
}