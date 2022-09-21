package com.hubject.datex.convert.converters.table.station.refill;

import com.hubject.datex.energyinfrastructure.generated.location_extension.AddressLine;
import com.hubject.datex.energyinfrastructure.generated.location_extension.AddressLineTypeEnum2;
import com.hubject.datex.energyinfrastructure.generated.location_referencing.PointLocation;
import com.hubject.datex.convert.model.datarecord.AddressIso19773Dto;
import com.hubject.datex.convert.model.datarecord.GeoCoordinatesDto;
import com.hubject.datex.convert.model.datarecord.GeoCoordinatesGoogleDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PointLocationConverterTest {

    @Test
    void shouldMapLongAndLatitude() {
        //given
        float latitude = 56.355122f;
        float longitude = 12.802945f;

        //when
        PointLocation pointLocation = PointLocationConverter.createPointLocation(createGeoCoordinatesDto(), createAddress());

        //then
        assertThat(pointLocation.getPointByCoordinates().getPointCoordinates().getLatitude()).isEqualTo(latitude);
        assertThat(pointLocation.getPointByCoordinates().getPointCoordinates().getLongitude()).isEqualTo(longitude);
    }

    @Test
    void shouldMapStreet() {
        //given
        AddressIso19773Dto address = createAddress();

        //when
        PointLocation pointLocation = PointLocationConverter.createPointLocation(createGeoCoordinatesDto(), address);

        //then
        List<AddressLine> addressLine = pointLocation.getLocationReferenceExtension().getFacilityLocation().getAddress().getAddressLine();
        assertThat(addressLine).hasSize(1);
        assertThat(addressLine.get(0).getType().getValue()).isEqualTo(AddressLineTypeEnum2.STREET);
        assertThat(addressLine.get(0).getText().getValues().getValue().get(0).getValue()).isEqualTo(address.getStreet());
    }

    private GeoCoordinatesDto createGeoCoordinatesDto() {
        return GeoCoordinatesDto.builder()
                .google(GeoCoordinatesGoogleDto.builder()
                        .coordinates("56.355122 12.802945")
                        .build())
                .build();
    }

    private AddressIso19773Dto createAddress() {
        return AddressIso19773Dto.builder().street("Street 12").build();
    }


}