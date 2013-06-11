
package org.foi.nwtis.lurajcevi.modeli;

import org.foi.nwtis.lurajcevi.ejb.jms.*;
import java.io.Serializable;

/**
 * @document JMSPorukaZip
 * @author Luka Rajcevic
 */
public class JMSPorukaZip implements Serializable {
    
    private String zip;

    public JMSPorukaZip(String zip) {
        this.zip = zip;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
