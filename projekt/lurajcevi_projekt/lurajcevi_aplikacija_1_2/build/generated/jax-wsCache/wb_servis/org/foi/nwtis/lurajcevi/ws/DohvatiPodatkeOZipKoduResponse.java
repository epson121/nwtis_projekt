
package org.foi.nwtis.lurajcevi.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import net.wxbug.api.MeteoPodaci;


/**
 * <p>Java class for dohvatiPodatkeOZipKoduResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dohvatiPodatkeOZipKoduResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://api.wxbug.net/}meteoPodaci" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dohvatiPodatkeOZipKoduResponse", propOrder = {
    "_return"
})
public class DohvatiPodatkeOZipKoduResponse {

    @XmlElement(name = "return")
    protected MeteoPodaci _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link MeteoPodaci }
     *     
     */
    public MeteoPodaci getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeteoPodaci }
     *     
     */
    public void setReturn(MeteoPodaci value) {
        this._return = value;
    }

}
