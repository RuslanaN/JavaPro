package net.ukr.ruslana;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ruslana on 12.04.16.
 */
public class Main {
    public static void main(String[] args) throws MalformedURLException, JAXBException {
        String request = "http://query.yahooapis.com/v1/public/yql?format=xml&q=select%20*%20from%20" +
                "yahoo.finance.xchange%20where%20pair%20in%20(\"USDEUR\",%20\"USDUAH\")&env=store://datatables.org/alltableswithkeys";
        URL url = new URL(request);
        JAXBContext jaxbContext = JAXBContext.newInstance(Query.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Query query = (Query) unmarshaller.unmarshal(url);
        System.out.println(query.getResults().toString());
        for (Rate rate : query.getResults().getRates()) {
            System.out.println(rate.getId() + "=" + rate.getRate());
        }
    }
}
