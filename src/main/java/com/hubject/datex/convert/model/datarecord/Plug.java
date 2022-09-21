package com.hubject.datex.convert.model.datarecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Plug {
    SMALL_PADDLE_INDUCTIVE("Small Paddle Inductive"),
    LARGE_PADDLE_INDUCTIVE("Large Paddle Inductive"),
    AVCON_CONNECTOR("AVCON Connector"),
    TESLA_CONNECTOR("Tesla Connector"),
    NEMA_5_20("NEMA 5-20"),
    TYPE_E_FRENCH_STANDARD("Type E French Standard"),
    TYPE_F_SCHUKO("Type F Schuko"),
    TYPE_G_BRITISH_STANDARD("Type G British Standard"),
    TYPE_J_SWISS_STANDARD("Type J Swiss Standard"),
    TYPE_1_CONNECTOR_CABLE_ATTACHED("Type 1 Connector (Cable Attached)"),
    TYPE_2_OUTLET("Type 2 Outlet"),
    TYPE_2_CONNECTOR_CABLE_ATTACHED("Type 2 Connector (Cable Attached)"),
    TYPE_3_OUTLET("Type 3 Outlet"),
    IEC_60309_SINGLE_PHASE("IEC 60309 Single Phase"),
    IES_60309_THREE_PHASE("IEC 60309 Three Phase"),
    CCS_COMBO_2_PLUG_CABLE_ATTACHED("CCS Combo 2 Plug (Cable Attached)"),
    CCS_COMBO_1_PLUG_CABLE_ATTACHED("CCS Combo 1 Plug (Cable Attached)"),
    CHADEMO("CHAdeMO");

    private final String value;

    @JsonValue
    public String getValue() {
        return value;
    }
}
