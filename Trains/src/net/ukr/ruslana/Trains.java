package net.ukr.ruslana;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ruslana on 11.04.16.
 */
@XmlRootElement(name = "trains")
public class Trains {
    @XmlElement(name = "train")
    private List<Train> tr = new ArrayList<>();

    public void add(Train train) {
        tr.add(train);
    }

    public void filterTime() {
        LocalTime startTime = LocalTime.of(15,0);
        LocalTime endTime = LocalTime.of(19,0);

        for (Train train : tr) {
            if ((train.getDeparture().isAfter(startTime) || train.getDeparture().equals(startTime))
                    && (train.getDeparture().isBefore(endTime) ||train.getDeparture().equals(endTime))) {
                System.out.println(train.toString());
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(tr.toArray());
    }
}
