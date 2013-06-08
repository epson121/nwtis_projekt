
package org.foi.nwtis.lurajcevi.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-2b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "wb_servis", targetNamespace = "http://ws.lurajcevi.nwtis.foi.org/", wsdlLocation = "http://localhost:8084/lurajcevi_aplikacija_1/wb_servis?wsdl")
public class WbServis_Service
    extends Service
{

    private final static URL WBSERVIS_WSDL_LOCATION;
    private final static WebServiceException WBSERVIS_EXCEPTION;
    private final static QName WBSERVIS_QNAME = new QName("http://ws.lurajcevi.nwtis.foi.org/", "wb_servis");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8084/lurajcevi_aplikacija_1/wb_servis?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WBSERVIS_WSDL_LOCATION = url;
        WBSERVIS_EXCEPTION = e;
    }

    public WbServis_Service() {
        super(__getWsdlLocation(), WBSERVIS_QNAME);
    }

    public WbServis_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), WBSERVIS_QNAME, features);
    }

    public WbServis_Service(URL wsdlLocation) {
        super(wsdlLocation, WBSERVIS_QNAME);
    }

    public WbServis_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WBSERVIS_QNAME, features);
    }

    public WbServis_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WbServis_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WbServis
     */
    @WebEndpoint(name = "wb_servisPort")
    public WbServis getWbServisPort() {
        return super.getPort(new QName("http://ws.lurajcevi.nwtis.foi.org/", "wb_servisPort"), WbServis.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WbServis
     */
    @WebEndpoint(name = "wb_servisPort")
    public WbServis getWbServisPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.lurajcevi.nwtis.foi.org/", "wb_servisPort"), WbServis.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WBSERVIS_EXCEPTION!= null) {
            throw WBSERVIS_EXCEPTION;
        }
        return WBSERVIS_WSDL_LOCATION;
    }

}