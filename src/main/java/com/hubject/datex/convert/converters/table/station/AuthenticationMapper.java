package com.hubject.datex.convert.converters.table.station;

import com.hubject.datex.energyinfrastructure.generated.infrastructure.AuthenticationAndIdentificationEnum;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.AuthenticationAndIdentificationEnum2;
import com.hubject.datex.convert.model.datarecord.AuthenticationMode;
import lombok.experimental.UtilityClass;

import java.util.EnumMap;
import java.util.Map;

import static com.hubject.datex.energyinfrastructure.generated.infrastructure.AuthenticationAndIdentificationEnum2.APPS;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.AuthenticationAndIdentificationEnum2.MIFARE_CLASSIC;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.AuthenticationAndIdentificationEnum2.MIFARE_DESFIRE;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.AuthenticationAndIdentificationEnum2.PLC;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.AuthenticationAndIdentificationEnum2.UNLIMITED_ACCESS;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.AuthenticationAndIdentificationEnum2.WEBSITE;
import static com.hubject.datex.convert.model.datarecord.AuthenticationMode.DIRECT_PAYMENT;
import static com.hubject.datex.convert.model.datarecord.AuthenticationMode.NFC_RFID_CLASSIC;
import static com.hubject.datex.convert.model.datarecord.AuthenticationMode.NFC_RFID_DESFIRE;
import static com.hubject.datex.convert.model.datarecord.AuthenticationMode.NO_AUTHENTICATION_REQUIRED;
import static com.hubject.datex.convert.model.datarecord.AuthenticationMode.PNC;
import static com.hubject.datex.convert.model.datarecord.AuthenticationMode.REMOTE;
import static java.lang.String.format;

@UtilityClass
class AuthenticationMapper {

    private final Map<AuthenticationMode, AuthenticationAndIdentificationEnum2> authenticationModeMap = new EnumMap<>(AuthenticationMode.class);

    static {
        authenticationModeMap.put(NFC_RFID_CLASSIC, MIFARE_CLASSIC);
        authenticationModeMap.put(NFC_RFID_DESFIRE, MIFARE_DESFIRE);
        authenticationModeMap.put(PNC, PLC);
        authenticationModeMap.put(REMOTE, APPS);
        authenticationModeMap.put(DIRECT_PAYMENT, WEBSITE);
        authenticationModeMap.put(NO_AUTHENTICATION_REQUIRED, UNLIMITED_ACCESS);
    }

    AuthenticationAndIdentificationEnum map(AuthenticationMode mode) {
        if (!authenticationModeMap.containsKey(mode)) {
            throw new IllegalArgumentException(format("AuthenticationMode: %s doesn't have mapping to DATEX II format", mode));
        }

        AuthenticationAndIdentificationEnum authenticationAndIdentificationWrapper = new AuthenticationAndIdentificationEnum();
        authenticationAndIdentificationWrapper.setValue(authenticationModeMap.get(mode));
        return authenticationAndIdentificationWrapper;
    }
}
