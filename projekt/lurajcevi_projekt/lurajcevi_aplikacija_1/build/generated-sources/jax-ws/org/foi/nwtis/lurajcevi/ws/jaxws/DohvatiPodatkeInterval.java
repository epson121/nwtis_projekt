
package org.foi.nwtis.lurajcevi.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dohvatiPodatkeInterval", namespace = "http://ws.lurajcevi.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dohvatiPodatkeInterval", namespace = "http://ws.lurajcevi.nwtis.foi.org/", propOrder = {
    "zip",
    "date1",
    "date2"
})
public class DohvatiPodatkeInterval {

    @XmlElement(name = "zip", namespace = "")
    private String zip;
    @XmlElement(name = "date1", namespace = "")
    private String date1;
    @XmlElement(name = "date2", namespace = "")
    private String date2;

    /**
     * 
     * @return
     *     returns String
     */
    public String getZip() {
        return this.zip;
    }

    /**
     * 
     * @param zip
     *     the value for the zip property
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getDate1() {
        return this.date1;
    }

    /**
     * 
     * @param date1
     *     the value for the date1 property
     */
    public void setDate1(String date1) {
        this.date1 = date1;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getDate2() {
        return this.date2;
    }

    /**
     * 
     * @param date2
     *     the value for the date2 property
     */
    public void setDate2(String date2) {
        this.date2 = date2;
    }

}
