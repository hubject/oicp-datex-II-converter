package com.hubject.datex.convert.converters;

import com.hubject.datex.convert.converters.table.station.StationConverter;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureSite;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureStation;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureTable;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureTablePublication;
import com.hubject.datex.convert.model.datarecord.PullEvseDataRecordDto;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class TablePublicationConverter {

    public EnergyInfrastructureTablePublication convert(List<PullEvseDataRecordDto> records) {
        EnergyInfrastructureTablePublication energyInfrastructureTablePublication = new EnergyInfrastructureTablePublication();
        EnergyInfrastructureTable energyInfrastructureTable = new EnergyInfrastructureTable();

        energyInfrastructureTable.getEnergyInfrastructureSite().addAll(toSites(records));

        energyInfrastructureTablePublication.getEnergyInfrastructureTable().add(energyInfrastructureTable);
        return energyInfrastructureTablePublication;

    }

    private List<EnergyInfrastructureSite> toSites(List<PullEvseDataRecordDto> records) {
        return records.stream().map(TablePublicationConverter::toSite).collect(Collectors.toList());
    }

    private EnergyInfrastructureSite toSite(PullEvseDataRecordDto recordDto) {
        EnergyInfrastructureSite site = new EnergyInfrastructureSite();
        EnergyInfrastructureStation station = StationConverter.createStation(recordDto);
        site.getEnergyInfrastructureStation().add(station);

        return site;
    }
}
