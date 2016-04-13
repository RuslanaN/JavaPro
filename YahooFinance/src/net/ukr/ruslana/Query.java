package net.ukr.ruslana;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ruslana on 12.04.16.
 */
@XmlRootElement(name = "query")
public class Query {
    private int count;
    private String created;
    private String lang;
    private Results results;

    public int getCount() {
        return count;
    }

    @XmlElement(name = "count")
    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    @XmlElement(name = "created")
    public void setCreated(String created) {
        this.created = created;
    }

    public String getLang() {
        return lang;
    }

    @XmlElement(name = "lang")
    public void setLang(String lang) {
        this.lang = lang;
    }

    public Results getResults() {
        return results;
    }

    @XmlElement(name = "results")
    public void setResults(Results results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Query{" +
                "count=" + count +
                ", created='" + created + '\'' +
                ", lang='" + lang + '\'' +
                ", results=" + results +
                '}';
    }
}
