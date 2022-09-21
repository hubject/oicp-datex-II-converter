package com.hubject.datex.convert.converters;

import com.hubject.datex.convert.converters.utils.ConverterUtils;
import com.hubject.datex.energyinfrastructure.generated.common.MultilingualString;
import com.hubject.datex.energyinfrastructure.generated.common.MultilingualStringValue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConverterUtilsTest {

    @Test
    void shouldConvertStringToMultilingualString() {
        //given
        String text = "text";
        MultilingualString expected = createExpectedResult(text);

        //when
        MultilingualString multilingualString = ConverterUtils.convertStringToMultilingualString(text);

        //then
        assertThat(multilingualString).usingRecursiveComparison().isEqualTo(expected);
    }

    private MultilingualString createExpectedResult(String text) {
        MultilingualString multilingualString = new MultilingualString();
        MultilingualString.Values values = new MultilingualString.Values();
        MultilingualStringValue value = new MultilingualStringValue();

        value.setValue(text);
        values.getValue().add(value);
        multilingualString.setValues(values);

        return multilingualString;
    }
}
