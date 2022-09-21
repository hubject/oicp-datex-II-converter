package com.hubject.datex.convert.converters.table.station.refill;

import com.hubject.datex.energyinfrastructure.generated.common.DayWeekMonth;
import com.hubject.datex.energyinfrastructure.generated.common.OverallPeriod;
import com.hubject.datex.energyinfrastructure.generated.common.Period;
import com.hubject.datex.energyinfrastructure.generated.common.TimePeriodOfDay;
import com.hubject.datex.energyinfrastructure.generated.facilities.OpenAllHours;
import com.hubject.datex.energyinfrastructure.generated.facilities.OperatingHours;
import com.hubject.datex.energyinfrastructure.generated.facilities.OperatingHoursSpecification;
import com.hubject.datex.convert.model.datarecord.OpeningTimesDto;
import com.hubject.datex.convert.model.datarecord.PeriodDto;
import com.hubject.datex.convert.model.datarecord.Weekday;
import lombok.experimental.UtilityClass;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
class OperatingHoursConverter {

    OperatingHours createOperatingHours(List<OpeningTimesDto> openingTimes, boolean isOpen24Hours) {
        if (isOpen24Hours) {
            return new OpenAllHours();
        }

        OperatingHoursSpecification operatingHoursSpecification = new OperatingHoursSpecification();
        operatingHoursSpecification.setOverallPeriod(createOverallPeriod(openingTimes));
        return operatingHoursSpecification;
    }

    private OverallPeriod createOverallPeriod(List<OpeningTimesDto> openingTimes) {
        OverallPeriod overallPeriod = new OverallPeriod();

        if (openingTimes == null) {
            return overallPeriod;
        }

        for (OpeningTimesDto openingTimesDto : openingTimes) {
            Period period = new Period();

            period.getRecurringTimePeriodOfDay().addAll(convertTimePeriods(openingTimesDto.getPeriod()));
            period.getRecurringDayWeekMonthPeriod().addAll(convertDays(openingTimesDto.getOn()));

            overallPeriod.getValidPeriod().add(period);
        }

        return overallPeriod;
    }

    private List<TimePeriodOfDay> convertTimePeriods(List<PeriodDto> periods) {
        List<TimePeriodOfDay> converted = new ArrayList<>();

        for (PeriodDto period : periods) {
            TimePeriodOfDay timePeriodOfDay = new TimePeriodOfDay();
            timePeriodOfDay.setStartTimeOfPeriod(LocalTime.parse(period.getBegin()));
            timePeriodOfDay.setEndTimeOfPeriod(LocalTime.parse(period.getEnd()));
            converted.add(timePeriodOfDay);
        }

        return converted;
    }

    private List<DayWeekMonth> convertDays(Weekday weekday) {
        DayWeekMonth dayWeekMonth = new DayWeekMonth();
        dayWeekMonth.getApplicableDay().addAll(WeekdayMapper.map(weekday));
        return List.of(dayWeekMonth);
    }
}
