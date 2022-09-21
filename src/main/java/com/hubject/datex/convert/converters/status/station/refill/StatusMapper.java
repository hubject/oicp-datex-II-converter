package com.hubject.datex.convert.converters.status.station.refill;

import com.hubject.datex.energyinfrastructure.generated.infrastructure.RefillPointStatusEnum;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.RefillPointStatusEnum2;
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
class StatusMapper {

    private final EnumMap<EvseStatus, RefillPointStatusEnum2> statusMap = new EnumMap<>(EvseStatus.class);

    static {
        statusMap.put(AVAILABLE, RefillPointStatusEnum2.AVAILABLE);
        statusMap.put(RESERVED, RefillPointStatusEnum2.RESERVED);
        statusMap.put(OCCUPIED, RefillPointStatusEnum2.OCCUPIED);
        statusMap.put(OUT_OF_SERVICE, RefillPointStatusEnum2.OUT_OF_ORDER);
        statusMap.put(EVSE_NOT_FOUND, RefillPointStatusEnum2.UNAVAILABLE);
        statusMap.put(UNKNOWN, RefillPointStatusEnum2.UNKNOWN);
    }

    RefillPointStatusEnum map(EvseStatus status) {
        if (!statusMap.containsKey(status)) {
            throw new IllegalArgumentException(format("Evse status: %s doesn't have mapping to DATEX II format", status));
        }

        RefillPointStatusEnum statusWrapper = new RefillPointStatusEnum();
        statusWrapper.setValue(statusMap.get(status));
        return statusWrapper;
    }
}
