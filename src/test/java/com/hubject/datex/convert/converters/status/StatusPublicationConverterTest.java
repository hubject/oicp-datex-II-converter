package com.hubject.datex.convert.converters.status;

import com.hubject.datex.convert.converters.StatusPublicationConverter;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureStatusPublication;
import com.hubject.datex.convert.model.status.EvseStatus;
import com.hubject.datex.convert.model.status.EvseStatusRecordDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StatusPublicationConverterTest {

    @Test
    void shouldMapOneEvseRecordToOneSite() {
        //given
        List<EvseStatusRecordDto> records = List.of(createRecord(), createRecord());

        //when
        EnergyInfrastructureStatusPublication publication = StatusPublicationConverter.convert(records);

        //then
        assertThat(publication.getEnergyInfrastructureSiteStatus()).hasSize(2);
    }

    private EvseStatusRecordDto createRecord() {
        return EvseStatusRecordDto.builder().evseStatus(EvseStatus.AVAILABLE).build();
    }
}
