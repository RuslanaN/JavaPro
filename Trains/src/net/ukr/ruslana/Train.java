package net.ukr.ruslana;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by ruslana on 11.04.16.
 */
@XmlRootElement(name = "train")
public class Train {
    private int id;
    private String from;
    private String to;
    private LocalDate date;
    private LocalTime departure;


    public Train(int id, String from, String to, LocalDate date, LocalTime departure) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.departure = departure;
    }

    public Train() {
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    @XmlElement(name = "from")
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    @XmlElement(name = "to")
    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    @XmlElement(name = "date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getDeparture() {
        return departure;
    }

    @XmlElement(name = "departure")
    @XmlJavaTypeAdapter(TimeAdapter.class)
    public void setDeparture(LocalTime departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "Train [" + id + ", " + from + ", " + to + ", " + date + ", " + departure + "]";
    }
}
