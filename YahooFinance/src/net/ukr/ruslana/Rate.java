package net.ukr.ruslana;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ruslana on 12.04.16.
 */
@XmlRootElement(name = "rate")
public class Rate {
    private String id;
    private String Name;
    private double Rate;
    private String Date;
    private String Time;
    private String Ask;
    private String Bid;

    public String getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        Name = name;
    }

    public double getRate() {
        return Rate;
    }

    @XmlElement(name = "Rate")
    public void setRate(double rate) {
        Rate = rate;
    }

    public String getDate() {
        return Date;
    }

    @XmlElement(name = "Date")
    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    @XmlElement(name = "Time")
    public void setTime(String time) {
        Time = time;
    }

    public String getAsk() {
        return Ask;
    }

    @XmlElement(name = "Ask")
    public void setAsk(String ask) {
        Ask = ask;
    }

    public String getBid() {
        return Bid;
    }

    @XmlElement(name = "Bid")
    public void setBid(String bid) {
        Bid = bid;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Rate=" + Rate +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                ", Ask='" + Ask + '\'' +
                ", Bid='" + Bid + '\'' +
                '}';
    }
}
