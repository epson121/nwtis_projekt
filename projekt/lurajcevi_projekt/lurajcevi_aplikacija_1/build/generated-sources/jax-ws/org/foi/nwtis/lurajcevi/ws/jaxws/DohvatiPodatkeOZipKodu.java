
package org.foi.nwtis.lurajcevi.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dohvatiPodatkeOZipKodu", namespace = "http://ws.lurajcevi.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dohvatiPodatkeOZipKodu", namespace = "http://ws.lurajcevi.nwtis.foi.org/")
public class DohvatiPodatkeOZipKodu {

    @XmlElement(name = "zip", namespace = "")
    private String zip;

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
