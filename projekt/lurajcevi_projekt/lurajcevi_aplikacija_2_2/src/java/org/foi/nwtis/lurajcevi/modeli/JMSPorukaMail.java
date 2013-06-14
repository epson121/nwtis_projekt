
package org.foi.nwtis.lurajcevi.modeli;

import java.io.Serializable;

/**
 * @document JMSPorukaMail
 * @author Luka Rajcevic
 */
public class JMSPorukaMail implements Serializable {
    
    public String vrijemePocetka;
    public String vrijemeZavrsetka;
    public int brojProcitanihPoruka;
    public int brojNwtisPoruka;
    
    /**
     * Struktura koja šalje JMS-om
     * @param vrijemePocetka - vrijeme
     * @param vrijemeZavrsetka - vrijeme
     * @param brojProcitanihPoruka - broj
     * @param brojNwtisPoruka  - broj
     */
    public JMSPorukaMail(String vrijemePocetka, String vrijemeZavrsetka, int brojProcitanihPoruka, int brojNwtisPoruka) {
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

    public int getBrojProcitanihPoruka() {
        return brojProcitanihPoruka;
    }

    public void setBrojProcitanihPoruka(int brojProcitanihPoruka) {
        this.brojProcitanihPoruka = brojProcitanihPoruka;
    }

    public int getBrojNwtisPoruka() {
        return brojNwtisPoruka;
    }

    public void setBrojNwtisPoruka(int brojNwtisPoruka) {
        this.brojNwtisPoruka = brojNwtisPoruka;
    }
    
}
