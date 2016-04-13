package net.ukr.ruslana;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by ruslana on 12.04.16.
 */
@XmlRootElement(name = "rates")
public class Results {
    @XmlElement(name = "rate")
    private ArrayList<Rate> rates = new ArrayList<>();

    public ArrayList<Rate> getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return "Results{" +
                "rates=" + rates +
                '}';
    }
}
