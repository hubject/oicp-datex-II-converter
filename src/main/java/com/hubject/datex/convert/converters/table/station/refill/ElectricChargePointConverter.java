package com.hubject.datex.convert.converters.table.station.refill;

import com.hubject.datex.energyinfrastructure.generated.facilities.ContactInformation;
import com.hubject.datex.energyinfrastructure.generated.facilities.OrganisationSpecification;
import com.hubject.datex.energyinfrastructure.generated.facilities.OrganisationUnit;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.Connector;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.ElectricChargingPoint;
import com.hubject.datex.convert.model.datarecord.ChargingFacilityDto;
import com.hubject.datex.convert.model.datarecord.Plug;
import com.hubject.datex.convert.model.datarecord.PullEvseDataRecordDto;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class ElectricChargePointConverter {

    public ElectricChargingPoint createElectricChargingPoint(PullEvseDataRecordDto record) {
        ElectricChargingPoint electricChargingPoint = new ElectricChargingPoint();

        electricChargingPoint.setId(record.getEvseId());
        addPower(electricChargingPoint, record);
        addVoltage(electricChargingPoint, record);
        addConnectors(electricChargingPoint, record);
        electricChargingPoint.setOperator(createOrganisationWithEmptyContactInformation());
        electricChargingPoint.setOperatingHours(OperatingHoursConverter.createOperatingHours(record.getOpeningTimes(), record.getIsOpen24Hours()));
        electricChargingPoint.setLocationReference(PointLocationConverter.createPointLocation(record.getGeoCoordinates(), record.getAddress()));

        return electricChargingPoint;
    }

    private static void addPower(ElectricChargingPoint electricChargingPoint, PullEvseDataRecordDto pullEvseDataRecordDto) {
        var chargingFacilities = pullEvseDataRecordDto.getChargingFacilities();
        if (chargingFacilities == null) {
            return;
        }
        var powers = convertMetric(chargingFacilities, ChargingFacilityDto::getPower);
        electricChargingPoint.getAvailableChargingPower().addAll(powers);
    }

    private static void addVoltage(ElectricChargingPoint electricChargingPoint, PullEvseDataRecordDto pullEvseDataRecordDto) {
        var chargingFacilities = pullEvseDataRecordDto.getChargingFacilities();
        if (chargingFacilities == null) {
            return;
        }
        var voltages = convertMetric(pullEvseDataRecordDto.getChargingFacilities(), ChargingFacilityDto::getVoltage);
        electricChargingPoint.getAvailableVoltage().addAll(voltages);
    }

    private static void addConnectors(ElectricChargingPoint electricChargingPoint, PullEvseDataRecordDto pullEvseDataRecordDto) {
        var plugs = pullEvseDataRecordDto.getPlugs();
        if (plugs == null) {
            return;
        }
        electricChargingPoint.getConnector().addAll(getConnectors(plugs));
    }

    private List<Float> convertMetric(List<ChargingFacilityDto> chargingFacilities,
                                             Function<ChargingFacilityDto, Double> getMetric) {
        return chargingFacilities.stream()
                .map(getMetric)
                .filter(Objects::nonNull)
                .map(Double::floatValue)
                .collect(Collectors.toList());
    }

    private List<Connector> getConnectors(List<Plug> plugs) {
        return plugs.stream()
                .map(plug -> {
                    Connector connector = new Connector();
                    connector.setConnectorType(ConnectorTypeMapper.map(plug));
                    return connector;
                })
                .collect(Collectors.toList());
    }

    private OrganisationSpecification createOrganisationWithEmptyContactInformation() {
        ContactInformation contactInformation = new ContactInformation();
        OrganisationUnit organisationUnit = new OrganisationUnit();
        organisationUnit.getContactInformation().add(contactInformation);
        OrganisationSpecification organisationSpecification = new OrganisationSpecification();
        organisationSpecification.getOrganisationUnit().add(organisationUnit);

        return organisationSpecification;
    }
}
