package com.hubject.datex.convert.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hubject.datex.convert.model.datarecord.PullEvseDataRecordDto;
import lombok.Value;

import java.util.List;

@Value
public class ConvertRecordsDto {
    List<PullEvseDataRecordDto> records;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ConvertRecordsDto(@JsonProperty("records") List<PullEvseDataRecordDto> records) {
        this.records = records;
    }
}