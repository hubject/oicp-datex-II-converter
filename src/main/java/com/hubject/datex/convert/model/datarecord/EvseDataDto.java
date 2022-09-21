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
public class EvseDataDto {

    private List<PullEvseDataRecordDto> content;
    private Integer number;
    private Integer size;
    private Integer totalElements;
    private Boolean last;
    private Integer totalPages;
    private Boolean first;
    private Integer numberOfElements;

    @JsonProperty("StatusCode")
    private StatusCodeDto statusCode;
}
