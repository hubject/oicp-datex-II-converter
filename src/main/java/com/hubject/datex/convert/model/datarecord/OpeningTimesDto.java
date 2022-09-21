package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class OpeningTimesDto {

    @JsonProperty("Period")
    private List<PeriodDto> period;
    private Weekday on;
}
