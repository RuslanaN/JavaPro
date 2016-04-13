package net.ukr.ruslana;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by ruslana on 11.04.16.
 */
public class Main {
    public static void main(String[] args) {
        Trains trains = new Trains();

        try {
            File file = new File("/home/ruslana/IdeaProjects/Trains/src/net/ukr/ruslana/Trains.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Trains.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            trains = (Trains) unmarshaller.unmarshal(file);
            System.out.println("Trains:" + trains);

            trains.add(new Train(3, "Kyiv", "Uzhgorod", LocalDate.of(2016,4,12), LocalTime.of(17,27)));
            trains.add(new Train(4, "Ivano-Frankivsk", "Dnipropetrovsk", LocalDate.of(2016,4,12), LocalTime.of(18,54)));
            trains.add(new Train(5, "Kyiv", "Odessa", LocalDate.of(2016,4,12), LocalTime.of(19,38)));
            trains.add(new Train(6, "Kyiv", "Odessa", LocalDate.of(2016,4,12), LocalTime.of(19,0)));
            trains.add(new Train(7, "Lviv", "Kharkiv", LocalDate.of(2016,4,12), LocalTime.of(15,0)));

            System.out.println("********************");
            System.out.println("New trains:" + trains);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(trains, file);
            marshaller.marshal(trains, System.out);
            System.out.println();
            System.out.println("********************");
            trains.filterTime();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
