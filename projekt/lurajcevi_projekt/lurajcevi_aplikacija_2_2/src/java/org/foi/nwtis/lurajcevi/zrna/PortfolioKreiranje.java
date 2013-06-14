/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.lurajcevi.ejb.eb.Cities;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviPortfolio;
import org.foi.nwtis.lurajcevi.ejb.eb.States;
import org.foi.nwtis.lurajcevi.ejb.eb.ZipCodes;
import org.foi.nwtis.lurajcevi.ejb.jms.ZipJMS;
import org.foi.nwtis.lurajcevi.ejb.sb.CitiesFacade;
import org.foi.nwtis.lurajcevi.ejb.sb.LurajceviPortfolioFacade;
import org.foi.nwtis.lurajcevi.ejb.sb.LurajceviZPFacade;
import org.foi.nwtis.lurajcevi.ejb.sb.StatesFacade;
import org.foi.nwtis.lurajcevi.ejb.sb.ZipCodesFacade;
import org.foi.nwtis.lurajcevi.slusaci.SlusacAplikacije;
import org.foi.nwtis.lurajcevi.ws.WSKlijent;

/**
 *
 * @author Luka Rajcevic
 */
@Named(value = "portfolioKreiranje")
@SessionScoped
public class PortfolioKreiranje implements Serializable {
    @EJB
    private ZipJMS zipJMS;
    @EJB
    private LurajceviZPFacade lurajceviZPFacade;
    @EJB
    private LurajceviPortfolioFacade lurajceviPortfolioFacade;
    @EJB
    private ZipCodesFacade zipCodesFacade;
    @EJB
    private StatesFacade statesFacade;
    @EJB
    private CitiesFacade citiesFacade;
    
    private Map<String, Object> popisDrzava;
    private List<String> popisDrzavaOdabrano;
    private Map<String, Object> odabraneDrzave;
    private List<String> odabraneDrzaveOdabrano;
    private String filterDrzava;
    
    private Map<String, Object> popisGradova;
    private List<String> popisGradovaOdabrano;
    private Map<String, Object> odabraniGradovi;
    private List<String> odabraniGradoviOdabrano;
    private String filterGradova;
    private List<Cities> gradovi;
    
    private Map<String, Object> popisZipKodova;
    private List<String> popisZipKodovaOdabrano;
    private Map<String, Object> odabraniZipKodovi;
    private List<String> odabraniZipKodoviOdabrano;
    private String filterZipKodova;
    
    private String nazivPortfolia;
    
    private List<String> aktivniZipKodovi;

    /**
     * *****************************************
     * KONSTRUKTOR 
     ******************************************
     */
    public PortfolioKreiranje() {
        context = FacesContext.getCurrentInstance();
        session = (HttpSession)(context.getExternalContext().getSession(true)); 
        aktivniZipKodovi = WSKlijent.dohvatiAktivneZipove();
    }
     
    private HttpSession session;
    private FacesContext context;
    
    private int minBrojZipova = Integer.parseInt(SlusacAplikacije.config.dajPostavku("min_zipovi"));
    private int maxBrojZipova = Integer.parseInt(SlusacAplikacije.config.dajPostavku("max_zipovi"));
  
    /**
     * *****************************************
     * POMOĆNE METODE 
     ******************************************
     */
    /**
     * Dohvaća odabrane drzave iz popisa drzava i stavlja ih u drugu listu 
     * (listu odabranih drzava)
     * @return vraća se na istu stranicu
     */
    public String dodajDrzave() {
        if (popisDrzavaOdabrano == null) {
            return "";
        }
        if (odabraneDrzave == null) {
            odabraneDrzave = new TreeMap<String, Object>();
        }
        for (String d : popisDrzavaOdabrano) {
            odabraneDrzave.put(d, d);
        }
        return "";
    }
    
    /**
     * brise drzavu iz popisa odabranih drzava
     * @return vraća se na istu stranicu
     */
    public String obrisiDrzave() {
        if (odabraneDrzaveOdabrano != null) {
            for (String d : odabraneDrzaveOdabrano) {
                odabraneDrzave.remove(d);
            }
        }
        return "";
    }
    
    /**
     * dohvaća popis gradova (nema implementacije jer je sohvaćanje napravljeno
     * u getteru za popisGradova)
     * @return vraća se na istu stranicu
     */
    public String preuzmiGradove() {
        return "";
    }
    
    /**
     * Dodaje odabrane gradove u novu listu (odabraniGradovi)
     * @return vraća se na istu stranicu
     */
    public String dodajGradove() {
        if (popisGradovaOdabrano == null || popisGradovaOdabrano.isEmpty()) {
            return "";
        }
        if (odabraniGradovi == null) {
            odabraniGradovi = new TreeMap<String, Object>();
        }
        for (String g : popisGradovaOdabrano) {
            odabraniGradovi.put(g, g);
        }
        return "";
    }
    
    /**
     * brise selektirane gradove iz popisa odabranih gradova
     * @return vraća se na istu stranicu
     */
    public String obrisiGradove() {
        if (odabraniGradoviOdabrano != null) {
            for (String g : odabraniGradoviOdabrano) {
                odabraniGradovi.remove(g);
            }
        }
        return "";
    }
    
    /**
     * dohvaća popis zip kodova u ovisnosti o odabranim gradovima i drzavama
     * implementacija je napravljena u getteru
     * @return vraća se na istu stranicu
     */
    public String preuzmiZipKodove() {
        return "";
    }
    
    /**
     * Dodaje odabrane zip kodove u novu listu odabranih zip kodova
     * @return vraća se na istu stranicu
     */
    public String dodajZipKodove() {
        if (popisZipKodovaOdabrano == null || popisZipKodovaOdabrano.isEmpty()) {
            return "";
        }
        if (odabraniZipKodovi == null) {
            odabraniZipKodovi = new TreeMap<String, Object>();
        }
        for (String z : popisZipKodovaOdabrano) {
            odabraniZipKodovi.put(z, z);
        }
        return "";
    }
    
    /**
     * brise odabrane zip kodove iz popisa odabranih zip kodova
     * @return vraća se na istu stranicu
     */
    public String obrisiZipKodove() {
        if (odabraniZipKodoviOdabrano != null) {
            for (String g : odabraniZipKodoviOdabrano) {
                odabraniZipKodovi.remove(g);
            }
        }
        return "";
    }
    
     /**
     * Sluzi za provjeru valjanosti unosa u polje. Ukoliko je unos prazan, 
     * ispisuje se poruka o grešci. Kao argumente prima reference na objekt iz forme
     * i vrijednost unesenog polja
     * @param context
     * @param component
     * @param value 
     */
    public void provjeriPrazno (FacesContext context, UIComponent component, Object value) {
        Locale l = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        String polje = (String) value;
        if (polje.equals("")){
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            if (l.equals(Locale.ENGLISH)){
                message.setDetail("Field can not be empty.");
            } else {
                message.setDetail("Polje ne smije biti prazno.");
            }
            context.addMessage(null, message);
            throw new ValidatorException(message);
        }
    }
    
    public String spremiPortfolio() throws NamingException{
        String korisnik = (String) session.getAttribute("korisnik");
        FacesContext c = FacesContext.getCurrentInstance();
        if (odabraniZipKodovi == null || odabraniZipKodovi.isEmpty()){
            return "";
        }
        if (odabraniZipKodovi.size() < minBrojZipova){
           c.addMessage(null, new FacesMessage("Potrebno je minimalno " + minBrojZipova + " kodova."));
           return "";
        } else if (odabraniZipKodovi.size() > maxBrojZipova){
            c.addMessage(null, new FacesMessage("Dozvoljeno je maksimalno " + maxBrojZipova + " kodova."));
            return "";
        }
        LurajceviPortfolio lp = lurajceviPortfolioFacade.dodajPortfolio(nazivPortfolia, korisnik);
        if (lp == null){
            c.addMessage(null, new FacesMessage("Taj portfolio već postoji."));
            return "";
        }
        for (String s : odabraniZipKodovi.keySet()){
            String[] data = s.split("-");
            String zip = data[3].trim();
            lurajceviZPFacade.dodajZP(lp, Integer.parseInt(zip));
            if (!aktivniZipKodovi.contains(zip)){
                try {
                    zipJMS.sendJMSMessageToNWTiS_lurajcevi_2(zip);
                } catch (JMSException ex) {
                    Logger.getLogger(PortfolioKreiranje.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "OK";
    }
    
   
    

    /**
     * *****************************************
     * GETTERI I SETTERI 
     ******************************************
     */
    public Map<String, Object> getPopisDrzava() {
        popisDrzava = new TreeMap<String, Object>();
        List<States> drzave;
        if (filterDrzava == null || filterDrzava.trim().isEmpty()) {
            drzave = statesFacade.findAll();
        } else {
            drzave = statesFacade.filtrirajDrzave(filterDrzava.toUpperCase());
        }
        for (States d : drzave) {
            popisDrzava.put(d.getName(), d.getName());
        }
        return popisDrzava;
    }

    public void setPopisDrzava(Map<String, Object> popisDrzava) {
        this.popisDrzava = popisDrzava;
    }

    public List<String> getPopisDrzavaOdabrano() {
        return popisDrzavaOdabrano;
    }

    public void setPopisDrzavaOdabrano(List<String> popisDrzavaOdabrano) {
        this.popisDrzavaOdabrano = popisDrzavaOdabrano;
    }

    public Map<String, Object> getOdabraneDrzave() {
        return odabraneDrzave;
    }

    public void setOdabraneDrzave(Map<String, Object> odabraneDrzave) {
        this.odabraneDrzave = odabraneDrzave;
    }

    public List<String> getOdabraneDrzaveOdabrano() {
        return odabraneDrzaveOdabrano;
    }

    public void setOdabraneDrzaveOdabrano(List<String> odabraneDrzaveOdabrano) {
        this.odabraneDrzaveOdabrano = odabraneDrzaveOdabrano;
    }

    public String getFilterDrzava() {
        return filterDrzava;
    }

    public void setFilterDrzava(String filterDrzava) {
        this.filterDrzava = filterDrzava;
    }

    public Map<String, Object> getPopisGradova() {
        popisGradova = new TreeMap<String, Object>();
        if (odabraneDrzave == null || odabraneDrzave.isEmpty()) {
            return popisGradova;
        }

        if (filterGradova == null || filterGradova.isEmpty()) {
            gradovi = citiesFacade.filtrirajGradove(odabraneDrzave.keySet());
        } else {
            gradovi = citiesFacade.filtrirajGradove(odabraneDrzave.keySet(), filterGradova.toUpperCase());
        }

        for (Cities c : gradovi) {
            String grad = c.getCitiesPK().getState() + " - " + c.getCitiesPK().getCounty() + " -  " + c.getCitiesPK().getCity();
            popisGradova.put(grad, grad);
        }
        return popisGradova;
    }

    public void setPopisGradova(Map<String, Object> popisGradova) {
        this.popisGradova = popisGradova;
    }

    public List<String> getPopisGradovaOdabrano() {
        return popisGradovaOdabrano;
    }

    public void setPopisGradovaOdabrano(List<String> popisGradovaOdabrano) {
        this.popisGradovaOdabrano = popisGradovaOdabrano;
    }

    public Map<String, Object> getOdabraniGradovi() {
        return odabraniGradovi;
    }

    public void setOdabraniGradovi(Map<String, Object> odabraniGradovi) {
        this.odabraniGradovi = odabraniGradovi;
    }

    public List<String> getOdabraniGradoviOdabrano() {
        return odabraniGradoviOdabrano;
    }

    public void setOdabraniGradoviOdabrano(List<String> odabraniGradoviOdabrano) {
        this.odabraniGradoviOdabrano = odabraniGradoviOdabrano;
    }

    public String getFilterGradova() {
        return filterGradova;
    }

    public void setFilterGradova(String filterGradova) {
        this.filterGradova = filterGradova;
    }

    public Map<String, Object> getPopisZipKodova() {
        popisZipKodova = new TreeMap<String, Object>();
        List<ZipCodes> zipovi;
        if (odabraniGradovi == null || odabraniGradovi.isEmpty()) {
            return popisZipKodova;
        }
        if (filterZipKodova == null || filterZipKodova.isEmpty()) {
            zipovi = zipCodesFacade.filtrirajZipove(odabraniGradovi.keySet());
        } else {
            zipovi = zipCodesFacade.filtrirajZipove(odabraniGradovi.keySet(), filterZipKodova);
        }
        for (ZipCodes zc : zipovi) {
            String res = zc.getCities().getCitiesPK().getState() + " - " + zc.getCities().getCitiesPK().getCounty() + " -  " + 
                         zc.getCities().getCitiesPK().getCity() + " - " + zc.getZip();
            popisZipKodova.put(res.toString(), res.toString());
        }
        return popisZipKodova;
    }

    public void setPopisZipKodova(Map<String, Object> popisZipKodova) {
        this.popisZipKodova = popisZipKodova;
    }

    public List<String> getPopisZipKodovaOdabrano() {
        return popisZipKodovaOdabrano;
    }

    public void setPopisZipKodovaOdabrano(List<String> popisZipKodovaOdabrano) {
        this.popisZipKodovaOdabrano = popisZipKodovaOdabrano;
    }

    public Map<String, Object> getOdabraniZipKodovi() {
        return odabraniZipKodovi;
    }

    public void setOdabraniZipKodovi(Map<String, Object> odabraniZipKodovi) {
        this.odabraniZipKodovi = odabraniZipKodovi;
    }

    public List<String> getOdabraniZipKodoviOdabrano() {
        return odabraniZipKodoviOdabrano;
    }

    public void setOdabraniZipKodoviOdabrano(List<String> odabraniZipKodoviOdabrano) {
        this.odabraniZipKodoviOdabrano = odabraniZipKodoviOdabrano;
    }

    public String getFilterZipKodova() {
        return filterZipKodova;
    }

    public void setFilterZipKodova(String filterZipKodova) {
        this.filterZipKodova = filterZipKodova;
    }

    public String getNazivPortfolia() {
        return nazivPortfolia;
    }

    public void setNazivPortfolia(String nazivPortfolia) {
        this.nazivPortfolia = nazivPortfolia;
    }

    
    
}
