
package org.foi.nwtis.lurajcevi.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import net.wxbug.api.MeteoPodaci;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-2b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "wb_servis", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/")
@XmlSeeAlso({
    net.wxbug.api.ObjectFactory.class,
    org.foi.nwtis.lurajcevi.ws.ObjectFactory.class
})
public interface WbServis {


    /**
     * 
     * @param zip
     * @return
     *     returns net.wxbug.api.MeteoPodaci
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dohvatiPodatkeOZipKodu", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.DohvatiPodatkeOZipKodu")
    @ResponseWrapper(localName = "dohvatiPodatkeOZipKoduResponse", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.DohvatiPodatkeOZipKoduResponse")
    @Action(input = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/dohvatiPodatkeOZipKoduRequest", output = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/dohvatiPodatkeOZipKoduResponse")
    public MeteoPodaci dohvatiPodatkeOZipKodu(
        @WebParam(name = "zip", targetNamespace = "")
        String zip);

    /**
     * 
     * @param zip
     * @param date2
     * @param date1
     * @return
     *     returns java.util.List<net.wxbug.api.MeteoPodaci>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dohvatiPodatkeInterval", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.DohvatiPodatkeInterval")
    @ResponseWrapper(localName = "dohvatiPodatkeIntervalResponse", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.DohvatiPodatkeIntervalResponse")
    @Action(input = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/dohvatiPodatkeIntervalRequest", output = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/dohvatiPodatkeIntervalResponse")
    public List<MeteoPodaci> dohvatiPodatkeInterval(
        @WebParam(name = "zip", targetNamespace = "")
        String zip,
        @WebParam(name = "date1", targetNamespace = "")
        String date1,
        @WebParam(name = "date2", targetNamespace = "")
        String date2);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "Hello")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "Hello", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.Hello")
    @ResponseWrapper(localName = "HelloResponse", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.HelloResponse")
    @Action(input = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/HelloRequest", output = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/HelloResponse")
    public String hello();

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dohvatiAktivneZipove", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.DohvatiAktivneZipove")
    @ResponseWrapper(localName = "dohvatiAktivneZipoveResponse", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.DohvatiAktivneZipoveResponse")
    @Action(input = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/dohvatiAktivneZipoveRequest", output = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/dohvatiAktivneZipoveResponse")
    public List<String> dohvatiAktivneZipove();

    /**
     * 
     * @param zip
     * @param n
     * @return
     *     returns java.util.List<net.wxbug.api.MeteoPodaci>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dohvatiPosljednjihNPodatakaZaZipKod", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.DohvatiPosljednjihNPodatakaZaZipKod")
    @ResponseWrapper(localName = "dohvatiPosljednjihNPodatakaZaZipKodResponse", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.DohvatiPosljednjihNPodatakaZaZipKodResponse")
    @Action(input = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/dohvatiPosljednjihNPodatakaZaZipKodRequest", output = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/dohvatiPosljednjihNPodatakaZaZipKodResponse")
    public List<MeteoPodaci> dohvatiPosljednjihNPodatakaZaZipKod(
        @WebParam(name = "n", targetNamespace = "")
        int n,
        @WebParam(name = "zip", targetNamespace = "")
        String zip);

    /**
     * 
     * @param n
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "dohvatiZipoveSNajvisePodataka", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.DohvatiZipoveSNajvisePodataka")
    @ResponseWrapper(localName = "dohvatiZipoveSNajvisePodatakaResponse", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", className = "org.foi.nwtis.lurajcevi.ws.DohvatiZipoveSNajvisePodatakaResponse")
    @Action(input = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/dohvatiZipoveSNajvisePodatakaRequest", output = "http://ws.lurajcevi.nwtis.foi.org/wb_servis/dohvatiZipoveSNajvisePodatakaResponse")
    public List<String> dohvatiZipoveSNajvisePodataka(
        @WebParam(name = "n", targetNamespace = "")
        int n);

}
