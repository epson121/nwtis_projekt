
package org.foi.nwtis.lurajcevi.modeli;

/**
 * @document Zahtjev
 * @author Luka Rajcevic
 */
public class Zahtjev {

    private int id;
    private String zahtjev;
    private String korisnik;
    private String datum;
    
    /**
     * Struktura podataka za prikaz zahtjeva prema serveru
     * @param id - broj zahtjeva
     * @param zahtjev - sam zahtjev
     * @param korisnik - korisnik koji ga je poslao
     * @param datum  - vrijeme
     */
    public Zahtjev(int id, String zahtjev, String korisnik, String datum) {
        this.id = id;
        this.zahtjev = zahtjev;
        this.korisnik = korisnik;
        this.datum = datum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZahtjev() {
        return zahtjev;
    }

    public void setZahtjev(String zahtjev) {
        this.zahtjev = zahtjev;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
    
    
    
}
