
package org.foi.nwtis.lurajcevi.ws.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.foi.nwtis.lurajcevi.ws.MeteoPodaci;

@XmlRootElement(name = "dohvatiPosljednjihNPodatakaZaZipKodResponse", namespace = "http://ws.lurajcevi.nwtis.foi.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dohvatiPosljednjihNPodatakaZaZipKodResponse", namespace = "http://ws.lurajcevi.nwtis.foi.org/")
public class DohvatiPosljednjihNPodatakaZaZipKodResponse {

    @XmlElement(name = "return", namespace = "")
    private List<MeteoPodaci> _return;

    /**
     * 
     * @return
     *     returns List<MeteoPodaci>
     */
    public List<MeteoPodaci> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<MeteoPodaci> _return) {
        this._return = _return;
    }

}
