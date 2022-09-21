package com.hubject.datex.convert.converters.table.station.refill;

import com.hubject.datex.energyinfrastructure.generated.common.DayEnum;
import com.hubject.datex.energyinfrastructure.generated.common.DayEnum2;
import com.hubject.datex.convert.model.datarecord.Weekday;
import lombok.experimental.UtilityClass;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hubject.datex.energyinfrastructure.generated.common.DayEnum2.FRIDAY;
import static com.hubject.datex.energyinfrastructure.generated.common.DayEnum2.MONDAY;
import static com.hubject.datex.energyinfrastructure.generated.common.DayEnum2.SATURDAY;
import static com.hubject.datex.energyinfrastructure.generated.common.DayEnum2.SUNDAY;
import static com.hubject.datex.energyinfrastructure.generated.common.DayEnum2.THURSDAY;
import static com.hubject.datex.energyinfrastructure.generated.common.DayEnum2.TUESDAY;
import static com.hubject.datex.energyinfrastructure.generated.common.DayEnum2.WEDNESDAY;
import static java.lang.String.format;

@UtilityClass
class WeekdayMapper {

    private final Map<Weekday, List<DayEnum2>> weekdayMap = new EnumMap<>(Weekday.class);

    static {
        weekdayMap.put(Weekday.MONDAY, List.of(MONDAY));
        weekdayMap.put(Weekday.TUESDAY,  List.of(TUESDAY));
        weekdayMap.put(Weekday.WEDNESDAY,  List.of(WEDNESDAY));
        weekdayMap.put(Weekday.THURSDAY,  List.of(THURSDAY));
        weekdayMap.put(Weekday.FRIDAY,  List.of(FRIDAY));
        weekdayMap.put(Weekday.SATURDAY,  List.of(SATURDAY));
        weekdayMap.put(Weekday.SUNDAY,  List.of(SUNDAY));
        weekdayMap.put(Weekday.WORKDAYS,  List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY));
        weekdayMap.put(Weekday.WEEKEND,  List.of(SATURDAY, SUNDAY));
        weekdayMap.put(Weekday.EVERYDAY,  List.of(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY));
    }

    List<DayEnum> map(Weekday weekday) {
        if (!weekdayMap.containsKey(weekday)) {
            throw new IllegalArgumentException(format("Weekday: %s doesn't have mapping to DATEX II format", weekday));
        }

        return weekdayMap.get(weekday).stream().map(WeekdayMapper::createDayEnumWrapper).collect(Collectors.toList());
    }

    private DayEnum createDayEnumWrapper(DayEnum2 day) {
        DayEnum dayEnumWrapper = new DayEnum();
        dayEnumWrapper.setValue(day);
        return dayEnumWrapper;
    }
}
