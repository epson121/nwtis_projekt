/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.foi.nwtis.lurajcevi.mb.ZipMB;
import org.foi.nwtis.lurajcevi.ws.RestClient;

/**
 *
 * @author Luka Rajcevic
 */
@Named(value = "podaciRest")
@SessionScoped
public class PodaciRest implements Serializable {

    private String[] korisnici;
    private String[] portfolio;
    private String[] zipovi;
    private String podaci;
    
    
    
    private String korisnikOdabrano;
    private String portfolioOdabrano;
    private String zipOdabrano;
    
    private RestClient rc = new RestClient();
    
    public PodaciRest() {
    }
    
    public String dohvatiPopisPortfolia(){
        zipOdabrano = null;
        portfolioOdabrano = null;
        return "";
    }
    
    public String dohvatiZipove(){
        return "";
    }
    
    public String dohvatiMeteoPodatke(){
        return "";
    }

    public String[] getKorisnici() {
        korisnici = rc.aktivniKorisnici("").split("<br/>");
        return korisnici;
    }

    public void setKorisnici(String[] korisnici) {
        this.korisnici = korisnici;
    }

    public String[] getPortfolio() {
        portfolio = null;
        if (korisnikOdabrano == null || korisnikOdabrano.equals("PRAZNO"))
            return null;
        portfolio = rc.aktivniKorisnici(korisnikOdabrano).split("<br/>");
        return portfolio;
    }

    public void setPortfolio(String[] portfolio) {
        this.portfolio = portfolio;
    }

    public String[] getZipovi() {
        if (korisnikOdabrano == null || portfolioOdabrano == null)
            return null;
        zipovi = rc.popisZip(korisnikOdabrano, portfolioOdabrano).split("<br/>");
        return zipovi;
    }

    public void setZipovi(String[] zipovi) {
        this.zipovi = zipovi;
    }

    public String getKorisnikOdabrano() {
        return korisnikOdabrano;
    }

    public void setKorisnikOdabrano(String korisnikOdabrano) {
        this.korisnikOdabrano = korisnikOdabrano;
    }

    public String getPortfolioOdabrano() {
        return portfolioOdabrano;
    }

    public void setPortfolioOdabrano(String portfolioOdabrano) {
        this.portfolioOdabrano = portfolioOdabrano;
    }

    public String getZipOdabrano() {
        return zipOdabrano;
    }

    public void setZipOdabrano(String zipOdabrano) {
        this.zipOdabrano = zipOdabrano;
    }

    public String getPodaci() {
        if (zipOdabrano == null)
            return "";
        podaci = ZipMB.sendRequest("USER user; GET ZIP " + zipOdabrano + ";");
        return podaci;
    }

    public void setPodaci(String podaci) {
        this.podaci = podaci;
    }
    
}
