
package org.foi.nwtis.lurajcevi.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.foi.nwtis.lurajcevi.ws.MeteoPodaci;

@XmlRootElement(name = "dohvatiPodatkeOZipKoduResponse", namespace = "http://ws.lurajcevi.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dohvatiPodatkeOZipKoduResponse", namespace = "http://ws.lurajcevi.nwtis.foi.org/")
public class DohvatiPodatkeOZipKoduResponse {

    @XmlElement(name = "return", namespace = "")
    private MeteoPodaci _return;

    /**
     * 
     * @return
     *     returns MeteoPodaci
     */
    public MeteoPodaci getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(MeteoPodaci _return) {
        this._return = _return;
    }

}
