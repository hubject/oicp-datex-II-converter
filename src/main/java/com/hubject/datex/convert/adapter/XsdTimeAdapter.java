package com.hubject.datex.convert.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;

public class XsdTimeAdapter extends XmlAdapter<String, LocalTime> {

    @Override
    public LocalTime unmarshal(String dateTime) {
        return LocalTime.parse(dateTime);
    }

    @Override
    public String marshal(LocalTime localTime) {
        return localTime.toString();
    }
}
