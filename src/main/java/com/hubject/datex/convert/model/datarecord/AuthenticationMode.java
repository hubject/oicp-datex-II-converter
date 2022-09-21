package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthenticationMode {
    NFC_RFID_CLASSIC("NFC RFID Classic"),
    NFC_RFID_DESFIRE("NFC RFID DESFire"),
    PNC("PnC"),
    REMOTE("REMOTE"),
    DIRECT_PAYMENT("Direct Payment"),
    NO_AUTHENTICATION_REQUIRED("No Authentication Required");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
