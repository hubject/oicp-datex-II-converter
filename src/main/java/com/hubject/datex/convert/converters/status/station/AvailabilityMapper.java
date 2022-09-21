package com.hubject.datex.convert.converters.status.station;

import com.hubject.datex.convert.model.status.EvseStatus;
import lombok.experimental.UtilityClass;

import java.util.EnumMap;

import static com.hubject.datex.convert.model.status.EvseStatus.AVAILABLE;
import static com.hubject.datex.convert.model.status.EvseStatus.EVSE_NOT_FOUND;
import static com.hubject.datex.convert.model.status.EvseStatus.OCCUPIED;
import static com.hubject.datex.convert.model.status.EvseStatus.OUT_OF_SERVICE;
import static com.hubject.datex.convert.model.status.EvseStatus.RESERVED;
import static com.hubject.datex.convert.model.status.EvseStatus.UNKNOWN;
import static java.lang.String.format;

@UtilityClass
class AvailabilityMapper {

    private final EnumMap<EvseStatus, Boolean> availabilityMap = new EnumMap<>(EvseStatus.class);

    static {
        availabilityMap.put(AVAILABLE, true);
        availabilityMap.put(RESERVED, false);
        availabilityMap.put(OCCUPIED, false);
        availabilityMap.put(OUT_OF_SERVICE, false);
        availabilityMap.put(EVSE_NOT_FOUND, false);
        availabilityMap.put(UNKNOWN, true);
    }

    Boolean map(EvseStatus evseStatus) {
        if (!availabilityMap.containsKey(evseStatus)) {
            throw new IllegalArgumentException(format("Evse status: %s doesn't have mapping in availability map", evseStatus));
        }
        return availabilityMap.get(evseStatus);
    }
}
