package com.hubject.datex.convert.converters;

import com.hubject.datex.convert.converters.status.station.StationConverter;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureSiteStatus;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureStationStatus;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureStatusPublication;
import com.hubject.datex.convert.model.status.EvseStatusRecordDto;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utilities for converting OICP Status records to DATEX II Status Publication records.
 */
@UtilityClass
public class StatusPublicationConverter {

    /**
     * Convert OICP Status records to DATEX II Status Publication records
     * @param records OICP Status records
     * @return DATEX II Status Publication records
     */
    public EnergyInfrastructureStatusPublication convert(Collection<EvseStatusRecordDto> records) {
        EnergyInfrastructureStatusPublication statusPublication = new EnergyInfrastructureStatusPublication();
        statusPublication.getEnergyInfrastructureSiteStatus().addAll(toSites(records));
        return statusPublication;
    }

    private List<EnergyInfrastructureSiteStatus> toSites(Collection<EvseStatusRecordDto> records) {
        return records.stream().map(StatusPublicationConverter::toSite).collect(Collectors.toList());
    }

    private EnergyInfrastructureSiteStatus toSite(EvseStatusRecordDto recordDto) {
        EnergyInfrastructureSiteStatus site = new EnergyInfrastructureSiteStatus();
        EnergyInfrastructureStationStatus station = StationConverter.createStation(recordDto);
        site.getEnergyInfrastructureStationStatus().add(station);

        return site;
    }
}
