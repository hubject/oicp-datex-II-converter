package com.hubject.datex.convert.converters.utils;

import com.hubject.datex.energyinfrastructure.generated.common.MultilingualString;
import com.hubject.datex.energyinfrastructure.generated.common.MultilingualStringValue;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConverterUtils {

    public MultilingualString convertStringToMultilingualString(String text) {
        MultilingualStringValue multilingualStringValue = new MultilingualStringValue();
        multilingualStringValue.setValue(text);

        MultilingualString.Values values = new MultilingualString.Values();
        values.getValue().add(multilingualStringValue);

        MultilingualString multilingualString = new MultilingualString();
        multilingualString.setValues(values);

        return multilingualString;
    }


}
