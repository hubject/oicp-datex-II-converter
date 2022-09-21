module datex {
    requires transitive java.xml;
    requires java.xml.bind;
    requires com.fasterxml.jackson.databind;
    requires lombok;
    requires org.slf4j;

    exports com.hubject.datex.convert.converters;
    exports com.hubject.datex.convert.model;
    exports com.hubject.datex.convert.model.datarecord;
    exports com.hubject.datex.convert.model.status;
    exports com.hubject.datex.energyinfrastructure.generated.cisinformation;
    exports com.hubject.datex.energyinfrastructure.generated.common;
    exports com.hubject.datex.energyinfrastructure.generated.common_extension;
    exports com.hubject.datex.energyinfrastructure.generated.exchangeinformation;
    exports com.hubject.datex.energyinfrastructure.generated.facilities;
    exports com.hubject.datex.energyinfrastructure.generated.informationmanagement;
    exports com.hubject.datex.energyinfrastructure.generated.infrastructure;
    exports com.hubject.datex.energyinfrastructure.generated.location_extension;
    exports com.hubject.datex.energyinfrastructure.generated.location_referencing;
    exports com.hubject.datex.energyinfrastructure.generated.messagecontainer;
    exports com.hubject.datex.energyinfrastructure.generated.payload;
    exports eu.datex2.wsdl.snapshotpull._2020;
}
