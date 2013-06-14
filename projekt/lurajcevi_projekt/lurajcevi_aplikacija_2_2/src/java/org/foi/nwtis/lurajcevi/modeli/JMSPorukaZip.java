
package org.foi.nwtis.lurajcevi.modeli;

import java.io.Serializable;

/**
 * @document JMSPorukaZip
 * @author Luka Rajcevic
 */
public class JMSPorukaZip implements Serializable {
    
    private String zip;
    
    /**
     * Struktura koja se šalje JMS porukom
     * @param zip - zip kod
     */
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
