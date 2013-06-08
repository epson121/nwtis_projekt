
package org.foi.nwtis.lurajcevi.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "dohvatiZipoveSNajvisePodataka", namespace = "http://ws.lurajcevi.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dohvatiZipoveSNajvisePodataka", namespace = "http://ws.lurajcevi.nwtis.foi.org/")
public class DohvatiZipoveSNajvisePodataka {

    @XmlElement(name = "n", namespace = "")
    private int n;

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

}
