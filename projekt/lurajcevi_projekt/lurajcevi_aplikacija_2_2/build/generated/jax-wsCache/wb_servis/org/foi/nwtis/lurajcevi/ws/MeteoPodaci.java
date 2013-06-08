
package org.foi.nwtis.lurajcevi.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for meteoPodaci complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="meteoPodaci">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="geo_duzina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="geo_sirina" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="grad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="temperatura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tlak" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vjetar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vlaga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zip_trazeni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zip_vraceni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "meteoPodaci", namespace = "http://api.wxbug.net/", propOrder = {
    "datum",
    "geoDuzina",
    "geoSirina",
    "grad",
    "temperatura",
    "tlak",
    "vjetar",
    "vlaga",
    "zipTrazeni",
    "zipVraceni"
})
public class MeteoPodaci {

    @XmlElement(namespace = "http://api.wxbug.net/")
    protected String datum;
    @XmlElement(name = "geo_duzina", namespace = "http://api.wxbug.net/")
    protected String geoDuzina;
    @XmlElement(name = "geo_sirina", namespace = "http://api.wxbug.net/")
    protected String geoSirina;
    @XmlElement(namespace = "http://api.wxbug.net/")
    protected String grad;
    @XmlElement(namespace = "http://api.wxbug.net/")
    protected String temperatura;
    @XmlElement(namespace = "http://api.wxbug.net/")
    protected String tlak;
    @XmlElement(namespace = "http://api.wxbug.net/")
    protected String vjetar;
    @XmlElement(namespace = "http://api.wxbug.net/")
    protected String vlaga;
    @XmlElement(name = "zip_trazeni", namespace = "http://api.wxbug.net/")
    protected String zipTrazeni;
    @XmlElement(name = "zip_vraceni", namespace = "http://api.wxbug.net/")
    protected String zipVraceni;

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatum(String value) {
        this.datum = value;
    }

    /**
     * Gets the value of the geoDuzina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeoDuzina() {
        return geoDuzina;
    }

    /**
     * Sets the value of the geoDuzina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeoDuzina(String value) {
        this.geoDuzina = value;
    }

    /**
     * Gets the value of the geoSirina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeoSirina() {
        return geoSirina;
    }

    /**
     * Sets the value of the geoSirina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeoSirina(String value) {
        this.geoSirina = value;
    }

    /**
     * Gets the value of the grad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrad() {
        return grad;
    }

    /**
     * Sets the value of the grad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrad(String value) {
        this.grad = value;
    }

    /**
     * Gets the value of the temperatura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemperatura() {
        return temperatura;
    }

    /**
     * Sets the value of the temperatura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemperatura(String value) {
        this.temperatura = value;
    }

    /**
     * Gets the value of the tlak property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTlak() {
        return tlak;
    }

    /**
     * Sets the value of the tlak property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTlak(String value) {
        this.tlak = value;
    }

    /**
     * Gets the value of the vjetar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVjetar() {
        return vjetar;
    }

    /**
     * Sets the value of the vjetar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVjetar(String value) {
        this.vjetar = value;
    }

    /**
     * Gets the value of the vlaga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVlaga() {
        return vlaga;
    }

    /**
     * Sets the value of the vlaga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVlaga(String value) {
        this.vlaga = value;
    }

    /**
     * Gets the value of the zipTrazeni property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipTrazeni() {
        return zipTrazeni;
    }

    /**
     * Sets the value of the zipTrazeni property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipTrazeni(String value) {
        this.zipTrazeni = value;
    }

    /**
     * Gets the value of the zipVraceni property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipVraceni() {
        return zipVraceni;
    }

    /**
     * Sets the value of the zipVraceni property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipVraceni(String value) {
        this.zipVraceni = value;
    }

}
