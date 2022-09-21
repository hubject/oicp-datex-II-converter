package com.hubject.datex.convert.converters.table.station.refill;

import com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum;
import com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2;
import com.hubject.datex.convert.model.datarecord.Plug;
import lombok.experimental.UtilityClass;

import java.util.EnumMap;
import java.util.Map;

import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.DOMESTIC_E;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.DOMESTIC_F;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.DOMESTIC_G;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.DOMESTIC_J;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.IEC_60309_X_2_SINGLE_16;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.IEC_60309_X_2_THREE_16;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.IEC_62196_T_1;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.IEC_62196_T_1_COMBO;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.IEC_62196_T_2;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.IEC_62196_T_2_COMBO;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.IEC_62196_T_3_A;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.OTHER;
import static com.hubject.datex.energyinfrastructure.generated.infrastructure.ConnectorTypeEnum2.TESLA_CONNECTOR_EUROPE;
import static com.hubject.datex.convert.model.datarecord.Plug.AVCON_CONNECTOR;
import static com.hubject.datex.convert.model.datarecord.Plug.CCS_COMBO_1_PLUG_CABLE_ATTACHED;
import static com.hubject.datex.convert.model.datarecord.Plug.CCS_COMBO_2_PLUG_CABLE_ATTACHED;
import static com.hubject.datex.convert.model.datarecord.Plug.CHADEMO;
import static com.hubject.datex.convert.model.datarecord.Plug.IEC_60309_SINGLE_PHASE;
import static com.hubject.datex.convert.model.datarecord.Plug.IES_60309_THREE_PHASE;
import static com.hubject.datex.convert.model.datarecord.Plug.LARGE_PADDLE_INDUCTIVE;
import static com.hubject.datex.convert.model.datarecord.Plug.NEMA_5_20;
import static com.hubject.datex.convert.model.datarecord.Plug.SMALL_PADDLE_INDUCTIVE;
import static com.hubject.datex.convert.model.datarecord.Plug.TESLA_CONNECTOR;
import static com.hubject.datex.convert.model.datarecord.Plug.TYPE_1_CONNECTOR_CABLE_ATTACHED;
import static com.hubject.datex.convert.model.datarecord.Plug.TYPE_2_CONNECTOR_CABLE_ATTACHED;
import static com.hubject.datex.convert.model.datarecord.Plug.TYPE_2_OUTLET;
import static com.hubject.datex.convert.model.datarecord.Plug.TYPE_3_OUTLET;
import static com.hubject.datex.convert.model.datarecord.Plug.TYPE_E_FRENCH_STANDARD;
import static com.hubject.datex.convert.model.datarecord.Plug.TYPE_F_SCHUKO;
import static com.hubject.datex.convert.model.datarecord.Plug.TYPE_G_BRITISH_STANDARD;
import static com.hubject.datex.convert.model.datarecord.Plug.TYPE_J_SWISS_STANDARD;
import static java.lang.String.format;

@UtilityClass
class ConnectorTypeMapper {

    private final Map<Plug, ConnectorTypeEnum2> plugMap = new EnumMap<>(Plug.class);

    static {
        plugMap.put(SMALL_PADDLE_INDUCTIVE, OTHER);
        plugMap.put(LARGE_PADDLE_INDUCTIVE, OTHER);
        plugMap.put(AVCON_CONNECTOR, OTHER);
        plugMap.put(TESLA_CONNECTOR, TESLA_CONNECTOR_EUROPE);
        plugMap.put(NEMA_5_20, OTHER);
        plugMap.put(TYPE_E_FRENCH_STANDARD, DOMESTIC_E);
        plugMap.put(TYPE_F_SCHUKO, DOMESTIC_F);
        plugMap.put(TYPE_G_BRITISH_STANDARD, DOMESTIC_G);
        plugMap.put(TYPE_J_SWISS_STANDARD, DOMESTIC_J);
        plugMap.put(TYPE_1_CONNECTOR_CABLE_ATTACHED, IEC_62196_T_1);
        plugMap.put(TYPE_2_CONNECTOR_CABLE_ATTACHED, IEC_62196_T_2);
        plugMap.put(TYPE_2_OUTLET, IEC_62196_T_2);
        plugMap.put(TYPE_3_OUTLET, IEC_62196_T_3_A);
        plugMap.put(IEC_60309_SINGLE_PHASE, IEC_60309_X_2_SINGLE_16);
        plugMap.put(IES_60309_THREE_PHASE, IEC_60309_X_2_THREE_16);
        plugMap.put(CCS_COMBO_2_PLUG_CABLE_ATTACHED, IEC_62196_T_2_COMBO);
        plugMap.put(CCS_COMBO_1_PLUG_CABLE_ATTACHED, IEC_62196_T_1_COMBO);
        plugMap.put(CHADEMO, ConnectorTypeEnum2.CHADEMO);
    }

    ConnectorTypeEnum map(Plug plug) {
        if (!plugMap.containsKey(plug)) {
            throw new IllegalArgumentException(format("Plug: %s doesn't have mapping to DATEX II format", plug));
        }

        ConnectorTypeEnum connectorTypeWrapper = new ConnectorTypeEnum();
        connectorTypeWrapper.setValue(plugMap.get(plug));
        return connectorTypeWrapper;
    }
}
