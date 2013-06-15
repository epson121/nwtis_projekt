/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviPortfolio;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviZP;
import org.foi.nwtis.lurajcevi.ejb.eb.ZipCodes;
import org.foi.nwtis.lurajcevi.ejb.sb.LurajceviPortfolioFacade;
import org.foi.nwtis.lurajcevi.ejb.sb.LurajceviZPFacade;
import org.foi.nwtis.lurajcevi.modeli.MeteoPodaciProsireno;
import org.foi.nwtis.lurajcevi.ws.MeteoPodaci;
import org.foi.nwtis.lurajcevi.ws.WSKlijent;

/**
 *
 * @author Luka Rajcevic
 */
@Named(value = "portfolioPregled")
@SessionScoped
public class PortfolioPregled implements Serializable {
    @EJB
    private LurajceviZPFacade lurajceviZPFacade;
    @EJB
    private LurajceviPortfolioFacade lurajceviPortfolioFacade;
    
    private String portfolioId;
    private LurajceviPortfolio lp;
    private List<LurajceviZP> lzp;
    private List<ZipCodes> zcl;
    private static List<MeteoPodaciProsireno> meteoPodaci;
    private static List<MeteoPodaciProsireno> meteoPodaciFilter;
    private MeteoPodaci mp;
    private MeteoPodaciProsireno mpp;
    
    private int pocetak = 0, stranicenje = 3;
    private static int viewId = 0;
    private boolean changed = false;
    
    private String datum1;
    private String datum2;
    
    /**
     * Creates a new instance of PortfolioPregled
     */
    public PortfolioPregled() {
    }
    
    public String udaljenostGeoKoordinata(double lat1, double lng1, double lat2, double lng2){
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;

        int meterConversion = 1609;

        return new Double(dist * meterConversion).floatValue() + "";

    }
    
     /**
     * Metoda za dohvaćanje prethodne stranice u popisu poruka
     * @return ""
     */
    public String prethodnaStranica(){
        if (pocetak - stranicenje <= 0){
            pocetak = 0;
        }
        else{
            pocetak = pocetak - stranicenje;
        }
        return "";
    }
    
    /**
     * Metoda za dohvaćanje slijedeće stranice u popisu poruka
     * @return ""
     */
    public String slijedecaStranica(){
        if (meteoPodaci != null){
            if (pocetak + stranicenje < meteoPodaci.size()){
                pocetak += stranicenje;   
            }
        }
        return "";
    }
    
    public String dohvatiPodatkeFiltrirano(){
        viewId = 1;
        setChanged(true);
        pocetak = 0;
        stranicenje = 3;
        return "";
    }
    

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
    
    public LurajceviPortfolio getLp() {
        return lp;
    }

    public void setLp(LurajceviPortfolio lp) {
        this.lp = lp;
    }

    public List<LurajceviZP> getLzp() {
        return lzp;
    }

    public void setLzp(List<LurajceviZP> lzp) {
        this.lzp = lzp;
    }

    public List<ZipCodes> getZcl() {
        return zcl;
    }

    public void setZcl(List<ZipCodes> zcl) {
        this.zcl = zcl;
    }

    public List<MeteoPodaciProsireno> getMeteoPodaci() throws ParseException {
        if (meteoPodaci == null || isChanged()){
            meteoPodaci = new ArrayList<MeteoPodaciProsireno>();
            setChanged(false);
        }
        else {
            int korak = pocetak + stranicenje;
            if (korak > meteoPodaci.size())
                 korak = meteoPodaci.size();
            return meteoPodaci.subList(pocetak, korak);
        }
        List<MeteoPodaci> listaMeteoPodataka;
        String udaljenost = "0";
        lp = lurajceviPortfolioFacade.dohvatiPortfolio(Integer.parseInt(portfolioId));
        System.out.println("PORTFOLIO ID: " + portfolioId);
        lzp = new ArrayList<LurajceviZP>();
        System.out.println("LP: " + lp);
        lzp = lurajceviZPFacade.dohvatiZP(lp);
        System.out.println("LZP: " + lzp);
        zcl = new ArrayList<ZipCodes>();
        for (LurajceviZP zp : lzp) {
            zcl.add(zp.getZipcode());
        }
        for (ZipCodes zc : zcl){
            if (viewId == 0){
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                mp = WSKlijent.dohvatiPodatkeOZipKodu(zc.getZip() + "");
                if (mp == null){
                    mpp =  new MeteoPodaciProsireno(zc.getZip() + "",
                                                       "N/A", "N/A", "N/A", "N/A", "N/A",
                                                       "N/A", "N/A", "N/A", "N/A", "N/A"
                                                      );
                    meteoPodaci.add(mpp);
                    continue;
                }
                if (zc.getZip()  != Integer.parseInt(mp.getZipVraceni())){
                    udaljenost = udaljenostGeoKoordinata(zc.getLatitude(), 
                                                         zc.getLongitude(),
                                                         Double.parseDouble(mp.getGeoSirina()),
                                                         Double.parseDouble(mp.getGeoDuzina())
                                                        );
                }
                mpp =  new MeteoPodaciProsireno(mp.getZipTrazeni(),
                                                       mp.getZipVraceni(),
                                                       mp.getTemperatura(),
                                                       mp.getVlaga(),
                                                       mp.getGeoDuzina(),
                                                       mp.getGeoSirina(),
                                                       mp.getGrad(),
                                                       mp.getVjetar(),
                                                       mp.getTlak(),
                                                       df.format(df2.parse(mp.getDatum())),
                                                       udaljenost
                                                      );
                meteoPodaci.add(mpp);
            }
            else {
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
                String dat1 = df2.format(df.parse(datum1));
                String dat2 = df2.format(df.parse(datum2));
                listaMeteoPodataka = WSKlijent.dohvatiPodatkeInterval(zc.getZip() + "", dat1, dat2);
                if (listaMeteoPodataka == null)
                    continue;
                for (MeteoPodaci mp2 : listaMeteoPodataka){
                    if (mp2.getZipVraceni() != null && !mp2.getZipVraceni().equals("null")){
                        if (zc.getZip()  != Integer.parseInt(mp2.getZipVraceni())){
                        udaljenost = udaljenostGeoKoordinata(zc.getLatitude(), 
                                                             zc.getLongitude(),
                                                             Double.parseDouble(mp2.getGeoSirina()),
                                                             Double.parseDouble(mp2.getGeoDuzina())
                                                            );
                        }
                    }
                    mpp =  new MeteoPodaciProsireno(mp2.getZipTrazeni(),
                                                           mp2.getZipVraceni(),
                                                           mp2.getTemperatura(),
                                                           mp2.getVlaga(),
                                                           mp2.getGeoDuzina(),
                                                           mp2.getGeoSirina(),
                                                           mp2.getGrad(),
                                                           mp2.getVjetar(),
                                                           mp2.getTlak(),
                                                           df.format(df2.parse(mp2.getDatum())),
                                                           udaljenost
                                                          );
                    meteoPodaci.add(mpp);
                }
            }
        }
        int korak = pocetak + stranicenje;
        if (korak > meteoPodaci.size())
            korak = meteoPodaci.size();
        return meteoPodaci.subList(pocetak, korak);
    }

    public static void setMeteoPodaci(List<MeteoPodaciProsireno> meteoPodaci) {
        PortfolioPregled.meteoPodaci = meteoPodaci;
    }

    public static int getViewId() {
        return viewId;
    }

    public static void setViewId(int viewId) {
        PortfolioPregled.viewId = viewId;
    }

    public String getDatum1() {
        return datum1;
    }

    public void setDatum1(String datum1) {
        this.datum1 = datum1;
    }

    public String getDatum2() {
        return datum2;
    }

    public void setDatum2(String datum2) {
        this.datum2 = datum2;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
    
    
    
}
