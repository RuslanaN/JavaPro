package net.ukr.ruslana;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ruslana on 12.04.16.
 */
public class TimeAdapter extends XmlAdapter <String, LocalTime> {

    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public String marshal(LocalTime v) throws Exception {
        synchronized (timeFormat) {
            return timeFormat.format(v);
        }
    }

    @Override
    public LocalTime unmarshal(String v) throws Exception {
        synchronized (timeFormat) {
            return LocalTime.parse(v, timeFormat);
        }
    }
}