
package org.foi.nwtis.lurajcevi.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dohvatiPosljednjihNPodatakaZaZipKod", namespace = "http://ws.lurajcevi.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dohvatiPosljednjihNPodatakaZaZipKod", namespace = "http://ws.lurajcevi.nwtis.foi.org/", propOrder = {
    "n",
    "zip"
})
public class DohvatiPosljednjihNPodatakaZaZipKod {

    @XmlElement(name = "n", namespace = "")
    private int n;
    @XmlElement(name = "zip", namespace = "")
    private String zip;

    /**
     * 
     * @return
     *     returns int
     */
    public int getN() {
        return this.n;
    }

    /**
     * 
     * @param n
     *     the value for the n property
     */
    public void setN(int n) {
        this.n = n;
    }

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

}
