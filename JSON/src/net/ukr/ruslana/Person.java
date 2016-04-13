package net.ukr.ruslana;

import java.util.Arrays;

/**
 * Created by ruslana on 12.04.16.
 */
public class Person {
    private String name;
    private String surname;
    private String[] phones;
    private String[] sites;
    private Address address;

    public Person(String name, String surname, String[] phones, String[] sites, Address address) {
        this.name = name;
        this.surname = surname;
        this.phones = phones;
        this.sites = sites;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phones=" + Arrays.toString(phones) +
                ", sites=" + Arrays.toString(sites) +
                ", address=" + address +
                '}';
    }
}
