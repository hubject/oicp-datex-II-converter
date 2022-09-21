package com.hubject.datex.convert.converters.table;

import com.hubject.datex.convert.converters.TablePublicationConverter;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.EnergyInfrastructureTablePublication;
import com.hubject.datex.convert.model.datarecord.PullEvseDataRecordDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TablePublicationConverterTest {

    @Test
    void shouldConvertEvseRecordToPublication() {
        //given
        List<PullEvseDataRecordDto> records = List.of(createRecord());

        //when
        EnergyInfrastructureTablePublication publication = TablePublicationConverter.convert(records);

        //then
        assertThat(publication.getEnergyInfrastructureTable().size()).isEqualTo(1);
    }

    @Test
    void shouldMapOneEvseRecordToOneSite() {
        //given
        List<PullEvseDataRecordDto> records = List.of(createRecord(), createRecord());

        //when
        EnergyInfrastructureTablePublication publication = TablePublicationConverter.convert(records);

        //then
        assertThat(publication.getEnergyInfrastructureTable().get(0).getEnergyInfrastructureSite().size()).isEqualTo(2);
    }

    private PullEvseDataRecordDto createRecord() {
        return CommonData.VALIDATED_RECORD_PART.build();
    }

}