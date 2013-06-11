
package org.foi.nwtis.lurajcevi.modeli;

import org.foi.nwtis.lurajcevi.ejb.jms.*;
import java.io.Serializable;

/**
 * @document JMSPorukaMail
 * @author Luka Rajcevic
 */
public class JMSPorukaMail implements Serializable {
    
    public String vrijemePocetka;
    public String vrijemeZavrsetka;
    public String brojProcitanihPoruka;
    public String brojNwtisPoruka;

    public JMSPorukaMail(String vrijemePocetka, String vrijemeZavrsetka, String brojProcitanihPoruka, String brojNwtisPoruka) {
        this.vrijemePocetka = vrijemePocetka;
        this.vrijemeZavrsetka = vrijemeZavrsetka;
        this.brojProcitanihPoruka = brojProcitanihPoruka;
        this.brojNwtisPoruka = brojNwtisPoruka;
    }

    public String getVrijemePocetka() {
        return vrijemePocetka;
    }

    public void setVrijemePocetka(String vrijemePocetka) {
        this.vrijemePocetka = vrijemePocetka;
    }

    public String getVrijemeZavrsetka() {
        return vrijemeZavrsetka;
    }

    public void setVrijemeZavrsetka(String vrijemeZavrsetka) {
        this.vrijemeZavrsetka = vrijemeZavrsetka;
    }

    public String getBrojProcitanihPoruka() {
        return brojProcitanihPoruka;
    }

    public void setBrojProcitanihPoruka(String brojProcitanihPoruka) {
        this.brojProcitanihPoruka = brojProcitanihPoruka;
    }

    public String getBrojNwtisPoruka() {
        return brojNwtisPoruka;
    }

    public void setBrojNwtisPoruka(String brojNwtisPoruka) {
        this.brojNwtisPoruka = brojNwtisPoruka;
    }
    
}
