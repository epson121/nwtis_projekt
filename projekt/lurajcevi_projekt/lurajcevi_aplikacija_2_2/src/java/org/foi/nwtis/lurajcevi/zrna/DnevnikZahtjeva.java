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
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviDnevnikZahtjeva;
import org.foi.nwtis.lurajcevi.ejb.sb.LurajceviDnevnikZahtjevaFacade;
import org.foi.nwtis.lurajcevi.modeli.Zahtjev;

/**
 *
 * @author Luka Rajcevic
 */
@Named(value = "dnevnikZahtjeva")
@SessionScoped
public class DnevnikZahtjeva implements Serializable {
    @EJB
    private LurajceviDnevnikZahtjevaFacade lurajceviDnevnikZahtjevaFacade;

    private static List<Zahtjev> popisZahtjeva;
    private List<LurajceviDnevnikZahtjeva> ldz;
    
    private int pocetak = 0, stranicenje = 3;
    private static int viewId = 0;
    
    private String datum1;
    private String datum2;
    private String korisnik;
    
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
    
    /**
     * Konstruktor, resetira pregled
     */
    public DnevnikZahtjeva() {
        viewId = 0;
        pocetak = 0;
        stranicenje = 3;
    }
    
    /**
     * Prikazuje podatke s prethodne stranice
     * @return 
     */
    public String prethodnaStranica(){
        if (pocetak - stranicenje <= 0){
            pocetak = 0;
        } else{
            pocetak = pocetak - stranicenje;
        }
        return "";
    }
    
    /**
     * Pokazuje podtke za slijedeću stranicu
     * @return 
     */
    public String slijedecaStranica(){
        if (popisZahtjeva != null){
            if (pocetak + stranicenje < popisZahtjeva.size()){
                pocetak += stranicenje;  
            }
        }
        return "";
    }
    
    /**
     * Dohvaća filtrirane podatke
     */
    public String dohvatiPodatkeFiltrirano(){
        popisZahtjeva = null;
        viewId = 1;
        pocetak = 0;
        stranicenje = 3;
        return "";
    }
    
    /**
     * Dohvaća podatke filtrirane po korisniku
     * @return 
     */
    public String dohvatiPremaKorisniku(){
        popisZahtjeva = null;
        viewId = 2;
        pocetak = 0;
        stranicenje = 3;
        return "";
    }

    /**
     * Getter za popis zahtjeva
     * Ovisno i viewId mijenjaju se podaci koji se prikazuju
     * @return
     */
    public List<Zahtjev> getPopisZahtjeva() {
        if (popisZahtjeva != null){
            int korak = pocetak + stranicenje;
            if (korak > popisZahtjeva.size())
                korak = popisZahtjeva.size();
            return popisZahtjeva.subList(pocetak, korak);
        }
        popisZahtjeva = new ArrayList<Zahtjev>();
        if (viewId == 0){
            ldz = lurajceviDnevnikZahtjevaFacade.dohvatiZahtjeve();
            for (LurajceviDnevnikZahtjeva l : ldz){
                Zahtjev z = new Zahtjev(l.getId(), l.getZahtjev(), l.getKorisnik(), df.format(l.getVrijeme()) + "");
                popisZahtjeva.add(z);
            }
        } else if (viewId == 1) {
            
            try{
                Date d1 = df2.parse(df2.format(df.parse(datum1)));
                Date d2 = df2.parse(df2.format(df.parse(datum2)));
                ldz = lurajceviDnevnikZahtjevaFacade.dohvatiZahtjeveFiltrirano(d1, d2);

                for (LurajceviDnevnikZahtjeva l : ldz){
                    Zahtjev z = new Zahtjev(l.getId(), l.getZahtjev(), l.getKorisnik(), df.format(l.getVrijeme()) + "");
                    popisZahtjeva.add(z);
                }
            } catch (ParseException pe){
                
            }
        } else if (viewId == 2) {
            System.out.println("KORISNIK: " + korisnik);
            ldz = lurajceviDnevnikZahtjevaFacade.dohvatiFiltriranoPoKorisniku(korisnik);
            for (LurajceviDnevnikZahtjeva l : ldz){
                Zahtjev z = new Zahtjev(l.getId(), l.getZahtjev(), l.getKorisnik(), df.format(l.getVrijeme()) + "");
                popisZahtjeva.add(z);
            }
        }
        int korak = pocetak + stranicenje;
        if (korak > popisZahtjeva.size())
            korak = popisZahtjeva.size();
        return popisZahtjeva.subList(pocetak, korak);
    }

    public static void setPopisZahtjeva(List<Zahtjev> popisZahtjeva) {
        DnevnikZahtjeva.popisZahtjeva = popisZahtjeva;
    }
    
    public static int getViewId() {
        return viewId;
    }

    public static void setViewId(int viewId) {
        DnevnikZahtjeva.viewId = viewId;
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

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }
    
    
}
