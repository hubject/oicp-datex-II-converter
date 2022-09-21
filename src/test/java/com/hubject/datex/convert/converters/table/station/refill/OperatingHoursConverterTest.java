package com.hubject.datex.convert.converters.table.station.refill;

import com.hubject.datex.energyinfrastructure.generated.common.DayEnum;
import com.hubject.datex.energyinfrastructure.generated.common.DayEnum2;
import com.hubject.datex.energyinfrastructure.generated.common.OverallPeriod;
import com.hubject.datex.energyinfrastructure.generated.common.TimePeriodOfDay;
import com.hubject.datex.energyinfrastructure.generated.facilities.OpenAllHours;
import com.hubject.datex.energyinfrastructure.generated.facilities.OperatingHours;
import com.hubject.datex.energyinfrastructure.generated.facilities.OperatingHoursSpecification;
import com.hubject.datex.convert.model.datarecord.OpeningTimesDto;
import com.hubject.datex.convert.model.datarecord.PeriodDto;
import com.hubject.datex.convert.model.datarecord.Weekday;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OperatingHoursConverterTest {

    @Test
    void shouldConvertTime() {
        //given
        List<OpeningTimesDto> openingTimesDto = List.of(new OpeningTimesDto(List.of(new PeriodDto("09:00", "18:00")), Weekday.MONDAY));

        //when
        OperatingHours operatingHours = OperatingHoursConverter.createOperatingHours(openingTimesDto, false);
        OperatingHoursSpecification specification = (OperatingHoursSpecification) operatingHours;

        //then
        TimePeriodOfDay timePeriodOfDay = specification.getOverallPeriod().getValidPeriod().get(0).getRecurringTimePeriodOfDay().get(0);
        assertThat(timePeriodOfDay.getStartTimeOfPeriod()).isEqualTo(LocalTime.of(9, 0, 0));
        assertThat(timePeriodOfDay.getEndTimeOfPeriod()).isEqualTo(LocalTime.of(18, 0, 0));
    }

    @Test
    void shouldConvertDays() {
        //given
        List<OpeningTimesDto> openingTimesDto = List.of(new OpeningTimesDto(List.of(new PeriodDto("09:00", "18:00")), Weekday.MONDAY));

        //when
        OperatingHours operatingHours = OperatingHoursConverter.createOperatingHours(openingTimesDto, false);
        OperatingHoursSpecification specification = (OperatingHoursSpecification) operatingHours;

        //then
        DayEnum day = specification.getOverallPeriod().getValidPeriod().get(0).getRecurringDayWeekMonthPeriod().get(0).getApplicableDay().get(0);
        assertThat(day.getValue()).isEqualTo(DayEnum2.MONDAY);
    }

    @Test
    void shouldConvertListOfOpeningTimesWithDifferentPeriods() {
        //given
        List<OpeningTimesDto> openingTimesDto = List.of(
                new OpeningTimesDto(List.of(new PeriodDto("06:00", "18:00"), new PeriodDto("20:00", "23:00")), Weekday.MONDAY),
                new OpeningTimesDto(List.of(new PeriodDto("05:00", "20:00"), new PeriodDto("21:00", "22:00")), Weekday.TUESDAY));

        //when
        OperatingHours operatingHours = OperatingHoursConverter.createOperatingHours(openingTimesDto, false);
        OperatingHoursSpecification specification = (OperatingHoursSpecification) operatingHours;

        //then
        OverallPeriod overallPeriod = specification.getOverallPeriod();
        List<TimePeriodOfDay> firstRecurringTimePeriodOfDay = overallPeriod.getValidPeriod().get(0).getRecurringTimePeriodOfDay();

        assertThat(overallPeriod.getValidPeriod().size()).isEqualTo(2);

        assertThat(overallPeriod.getValidPeriod().get(0).getRecurringDayWeekMonthPeriod().get(0).getApplicableDay().get(0).getValue()).isEqualTo(DayEnum2.MONDAY);
        assertThat(firstRecurringTimePeriodOfDay.get(0).getStartTimeOfPeriod()).isEqualTo(LocalTime.of(6, 0, 0));
        assertThat(firstRecurringTimePeriodOfDay.get(0).getEndTimeOfPeriod()).isEqualTo(LocalTime.of(18, 0, 0));
        assertThat(firstRecurringTimePeriodOfDay.get(1).getStartTimeOfPeriod()).isEqualTo(LocalTime.of(20, 0, 0));
        assertThat(firstRecurringTimePeriodOfDay.get(1).getEndTimeOfPeriod()).isEqualTo(LocalTime.of(23, 0, 0));

        List<TimePeriodOfDay> secondRecurringTimePeriodOfDay = overallPeriod.getValidPeriod().get(1).getRecurringTimePeriodOfDay();

        assertThat(overallPeriod.getValidPeriod().get(1).getRecurringDayWeekMonthPeriod().get(0).getApplicableDay().get(0).getValue()).isEqualTo(DayEnum2.TUESDAY);
        assertThat(secondRecurringTimePeriodOfDay.get(0).getStartTimeOfPeriod()).isEqualTo(LocalTime.of(5, 0, 0));
        assertThat(secondRecurringTimePeriodOfDay.get(0).getEndTimeOfPeriod()).isEqualTo(LocalTime.of(20, 0, 0));
        assertThat(secondRecurringTimePeriodOfDay.get(1).getStartTimeOfPeriod()).isEqualTo(LocalTime.of(21, 0));
        assertThat(secondRecurringTimePeriodOfDay.get(1).getEndTimeOfPeriod()).isEqualTo(LocalTime.of(22, 0, 0));
    }

    @Test
    void shouldReturnOpen24HoursInstance() {
        //given
        List<OpeningTimesDto> openingTimes = List.of();

        //when
        OperatingHours operatingHours = OperatingHoursConverter.createOperatingHours(openingTimes, true);

        //then
        assertThat(operatingHours).isInstanceOf(OpenAllHours.class);
    }
}