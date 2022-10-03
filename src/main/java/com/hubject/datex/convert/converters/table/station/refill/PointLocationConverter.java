package com.hubject.datex.convert.converters.table.station.refill;

import com.hubject.datex.convert.converters.utils.ConverterUtils;
import com.hubject.datex.energyinfrastructure.generated.location_extension.Address;
import com.hubject.datex.energyinfrastructure.generated.location_extension.AddressLine;
import com.hubject.datex.energyinfrastructure.generated.location_extension.AddressLineTypeEnum;
import com.hubject.datex.energyinfrastructure.generated.location_extension.AddressLineTypeEnum2;
import com.hubject.datex.energyinfrastructure.generated.location_extension.FacilityLocation;
import com.hubject.datex.energyinfrastructure.generated.location_referencing.LocationReferenceExtensionType;
import com.hubject.datex.energyinfrastructure.generated.location_referencing.PointByCoordinates;
import com.hubject.datex.energyinfrastructure.generated.location_referencing.PointCoordinates;
import com.hubject.datex.energyinfrastructure.generated.location_referencing.PointLocation;
import com.hubject.datex.convert.model.datarecord.AddressIso19773Dto;
import com.hubject.datex.convert.model.datarecord.GeoCoordinatesDto;
import lombok.experimental.UtilityClass;

@UtilityClass
class PointLocationConverter {
    private static final String SPLIT_REGEX = " ";

    PointLocation createPointLocation(GeoCoordinatesDto geoCoordinatesDto, AddressIso19773Dto address) {
        PointLocation pointLocation = new PointLocation();

        pointLocation.setPointByCoordinates(getPointByCoordinates(geoCoordinatesDto));
        pointLocation.setLocationReferenceExtension(getLocationReference(address));

        return pointLocation;
    }

    private LocationReferenceExtensionType getLocationReference(AddressIso19773Dto addressDto) {
        AddressLine addressLine = new AddressLine();
        AddressLineTypeEnum addressLineTypeEnum = new AddressLineTypeEnum();
        addressLineTypeEnum.setValue(AddressLineTypeEnum2.STREET);
        addressLine.setType(addressLineTypeEnum);
        addressLine.setText(ConverterUtils.convertStringToMultilingualString(addressDto.getStreet()));

        Address address = new Address();
        address.getAddressLine().add(addressLine);

        FacilityLocation facilityLocation = new FacilityLocation();
        facilityLocation.setAddress(address);

        LocationReferenceExtensionType locationReferenceExtensionType = new LocationReferenceExtensionType();
        locationReferenceExtensionType.setFacilityLocation(facilityLocation);

        return locationReferenceExtensionType;
    }

    private PointByCoordinates getPointByCoordinates(GeoCoordinatesDto geoCoordinatesDto) {
        PointByCoordinates pointByCoordinates = new PointByCoordinates();
        PointCoordinates pointCoordinates = new PointCoordinates();

        String[] coordinates = geoCoordinatesDto.getGoogle().getCoordinates().split(SPLIT_REGEX);
        pointCoordinates.setLatitude(Float.parseFloat(coordinates[0]));
        pointCoordinates.setLongitude(Float.parseFloat(coordinates[1]));

        pointByCoordinates.setPointCoordinates(pointCoordinates);

        return pointByCoordinates;
    }
}
