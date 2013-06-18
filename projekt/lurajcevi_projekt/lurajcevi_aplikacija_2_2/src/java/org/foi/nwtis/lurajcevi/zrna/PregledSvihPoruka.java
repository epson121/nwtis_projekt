/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.zrna;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.AuthenticationFailedException;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.FolderNotFoundException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.ReadOnlyFolderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.StoreClosedException;
import javax.mail.internet.InternetAddress;
import org.foi.nwtis.lurajcevi.modeli.Poruka;
import org.foi.nwtis.lurajcevi.modeli.PrivitakPoruke;
import org.foi.nwtis.lurajcevi.slusaci.SlusacAplikacije;

/**
 *
 * @author Luka Rajcevic
 */
@ManagedBean(name = "pregledSvihPoruka")
@SessionScoped
public class PregledSvihPoruka implements Serializable {

     /*******************************
     * VARIJABLE
     * ****************************
     */
    private String emailPosluzitelj = "";
    private String korisnickoIme = "";
    private String lozinka = "";
    private List<Poruka> popisPoruka = new ArrayList<Poruka>();
    private Poruka odabranaPoruka;
    private String porukaID;
    private List<String> popisMapa;
    private String odabranaMapa = "INBOX";
    private int pocetak = 0, stranicenje = 3, brojPoruka = 0;
    private boolean next = false, prev = false;
    private boolean praznaMapa = true;
    
    private SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
    
    /*******************************
     * KONSTRUKTOR
     * ****************************
     */
    
    
    /**
     * KOnstruktor za pregled svih poruka
     * Dohvaća potrebne podatke iz drugih beanova
     */
    public PregledSvihPoruka() {
        emailPosluzitelj = SlusacAplikacije.config.dajPostavku("emailPosluzitelj");
        korisnickoIme = SlusacAplikacije.config.dajPostavku("username");
        lozinka = SlusacAplikacije.config.dajPostavku("password");
        preuzmiPoruke(pocetak, stranicenje);
    }
    
     /********************************
     * POMOĆNE METODE
     * ******************************
     */
    /**
     * Sluzi za navigaciju na stranicu za pregled pojedine poruke
     * @return OK - ukoliko je poruka pronađena, ERROR ukoliko poruka ne postoji,
     * inače NOT_OK
     * 
     */
    public String pregledPoruke(){
        odabranaPoruka = null;
        for(Poruka p : popisPoruka) {
            if (p.getId() != null){
                if(p.getId().equals(porukaID)) {
                    odabranaPoruka = p;
                    return "OK";
                }
            } else{
                return "ERROR";
            }
        }
        return "NOT_OK";
    }
    
    public String obrisiPoruku(){
        pocetak = 0;
        stranicenje = 3;
        Session session = null;
        Store store = null;
        Folder folder = null;
        Message message = null;
        Message[] messages = null;
        try{
            session = Session.getDefaultInstance(System.getProperties(), null);
            store = session.getStore("imap");
            store.connect(emailPosluzitelj, korisnickoIme, lozinka);
            
            folder = store.getDefaultFolder();
            folder = folder.getFolder(odabranaMapa);
            folder.open(Folder.READ_ONLY);
            
            messages = folder.getMessages();
            for (int messageNumber = 0; messageNumber < messages.length;  messageNumber++) {
                message = messages[messageNumber];
                if (message.getHeader("Message-ID")[0].equals(porukaID)){
                    message.setFlag(Flags.Flag.DELETED, true);
                    return "";
                }
            }
        } catch (MessagingException me){
            
        }
        return "";
    }
    
     /**
     * Pomocna metoda, sluzi za brisanje poruka iz svih mapa
     * Ne koristi se u zadaci, ali je bila korisna prilikom testiranja i debugiranja
     */
    public void obrisiSvePoruke(){
        
        Session session;
        Store store;
        Folder folder;
        Message[] messages;
        List<String> folders = new ArrayList<String>();
        folders.add(odabranaMapa);
        for (String f : folders){
            try{
                session = Session.getDefaultInstance(System.getProperties(), null);
                store = session.getStore("imap");
                store.connect(emailPosluzitelj, korisnickoIme, lozinka);
                folder = store.getDefaultFolder();
                folder = folder.getFolder(f);
                
                folder.open(Folder.READ_WRITE);
                messages = folder.getMessages();
                System.out.println("FOLDER " + f + " DELETED.");
                System.out.println("mess count: " + messages.length);
                for (int messageNumber = 0; messageNumber < messages.length; messageNumber++) {
                    messages[messageNumber].setFlag(Flags.Flag.DELETED, true);
                }
                folder.close(true);
                store.close();
            } catch (NoSuchProviderException ex) {
                Logger.getLogger(PregledSvihPoruka.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(PregledSvihPoruka.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * sluzi za odabir mape i prikupljanje poruka iz pojedine mape
     * @return 
     */
    public String odaberiMapu(){
        pocetak = 0;
        getPopisPoruka();
        return "";
    }
    
    /**
     * sluzi za preuzimanje poruka iz odabrane mape. Preuzimaju se najsviježije poruke
     * i to broj određen konfiguracijom (radi straničenja). 
     * @param pocetak - broj početne poruke 
     * @param korak - broj poruka koji se treba prikupiti
     */
    private void preuzmiPoruke(int pocetak, int korak) {
        Session session = null;
        Store store = null;
        Folder folder = null;
        Message message = null;
        Message[] messages = null;
        Object messagecontentObject = null;
        String sender = null;
        String subject = null;
        Multipart multipart = null;
        Part part = null;
        String contentType = null;
        popisPoruka.clear();
        
        try{ 
            session = Session.getDefaultInstance(System.getProperties(), null);
            store = session.getStore("imap");
            store.connect(emailPosluzitelj, korisnickoIme, lozinka);
            folder = store.getDefaultFolder();

            folder = folder.getFolder(odabranaMapa);
            folder.open(Folder.READ_ONLY);
            messages = folder.getMessages();
            int n = messages.length;
            if (n == 0){
                setPraznaMapa(true);
            } else{
                setPraznaMapa(false);
            }
            if (pocetak <= 0){
                pocetak = 0;
                this.pocetak = 0;
                setNext(true);
                setPrev(false);
                if (pocetak + korak >= n){
                    korak = n - pocetak;
                    setNext(false);
                }
            } else if (pocetak > n){
                this.pocetak = this.pocetak - korak;
                pocetak = pocetak - korak;
                setNext(false);
                setPrev(true);
                if (pocetak + korak > n){
                    korak = n - pocetak;
                    setNext(false);
                }
            } else if (pocetak == n){
                this.pocetak = this.pocetak - korak;
                pocetak = pocetak - korak;
                setNext(false);
                setPrev(true);
            } else if (pocetak + korak > n){
                    korak = n - pocetak;
                    setNext(false);
                    setPrev(true);
            } else if (pocetak + korak == n){
                setNext(false);
                setPrev(true);
            }
            else{
                setPrev(true);
                setNext(true);
            }
            for (int messageNumber = n - 1 - pocetak; messageNumber > n - 1 - korak - pocetak;  messageNumber--) {
                message = messages[messageNumber];
                messagecontentObject = message.getContent();

                if (messagecontentObject instanceof Multipart) {
                    sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
                    
                    if (sender == null) {
                        sender = ((InternetAddress) message.getFrom()[0]).getAddress();
                    }
                    subject = message.getSubject();
                    multipart = (Multipart) message.getContent();

                    List<PrivitakPoruke> privitciPoruke = new ArrayList<PrivitakPoruke>();
                    String tekstPoruke = "";
                    for (int i = 0; i < multipart.getCount(); i++) {
                        part = multipart.getBodyPart(i);
                        contentType = part.getContentType();

                        String fileName = "";
                        if (contentType.startsWith("text/plain") || contentType.startsWith("TEXT/PLAIN") ||
                            contentType.startsWith("text/html") || contentType.startsWith("TEXT/HTML")) {
                            tekstPoruke = part.getContent().toString().replace("\n", "<br/>");
                        } else {
                            fileName = part.getFileName();
                        }
                        PrivitakPoruke privitak = new PrivitakPoruke(i, contentType, part.getSize(), fileName);
                        privitciPoruke.add(privitak);
                    }
                    String[] zaglavlje = message.getHeader("Message-ID");
                    porukaID = "";
                    if (zaglavlje != null && zaglavlje.length > 0) {
                        porukaID = zaglavlje[0];
                    }
                    Poruka poruka = new Poruka(porukaID, message.getSentDate(), sender, subject, message.getContentType(),
                                               message.getSize(), privitciPoruke.size(), message.getFlags(), privitciPoruke, true, true, tekstPoruke);
                    popisPoruka.add(poruka);
                    
                } else {
                    sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
                    if (sender == null) {
                        sender = ((InternetAddress) message.getFrom()[0]).getAddress();
                    }
                    subject = message.getSubject();
                    porukaID = "";
                    String[] zaglavlje = message.getHeader("Message-ID");
                    if (zaglavlje != null && zaglavlje.length > 0) {
                        porukaID = zaglavlje[0];
                    }
                     Poruka poruka = new Poruka(porukaID, message.getSentDate(), sender, subject, message.getContentType(),
                                                message.getSize(), 0, message.getFlags(), null, true, true, message.getContent().toString().replace("\n", "<br/>"));
                     popisPoruka.add(poruka);
                }
            }
            if (folder.isOpen())
                folder.close(true);
            if (store.isConnected())
                store.close();
        } catch (AuthenticationFailedException e) {
            e.printStackTrace();
        } catch (FolderClosedException e) {
            e.printStackTrace();
        } catch (FolderNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (ReadOnlyFolderException e) {
            e.printStackTrace();
        } catch (StoreClosedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metoda koja prolazi kroz popis svih mapa u mail serveru i dodaje 
     * ih u listu svih mapa
     */
    public void dohvatiMape(){
        popisMapa = new ArrayList<String>();
        try {
            Session session = null;
            Store store = null;
            
            session = Session.getDefaultInstance(System.getProperties(), null);
            store = session.getStore("imap");

            store.connect(emailPosluzitelj, korisnickoIme, lozinka);
            Folder[] f = store.getDefaultFolder().list();
            for(Folder fd : f)
                popisMapa.add(fd.getName());
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(PregledSvihPoruka.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(PregledSvihPoruka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metoda za dohvaćanje prethodne stranice u popisu poruka
     * @return ""
     */
    public String prethodnaStranica(){
        pocetak = pocetak - stranicenje;
        preuzmiPoruke(pocetak, stranicenje);
        setBrojPoruka(popisPoruka.size());
        return "";
    }
    
    /**
     * Metoda za dohvaćanje slijedeće stranice u popisu poruka
     * @return ""
     */
    public String slijedecaStranica(){
        pocetak = pocetak + stranicenje;
        preuzmiPoruke(pocetak, stranicenje);
        setBrojPoruka(popisPoruka.size());
        return "";
    }
    
    /********************************
     * GETTERI I SETTERI
     * ******************************
     */
    
    public String getEmailPosluzitelj() {
        return emailPosluzitelj;
    }

    public void setEmailPosluzitelj(String emailPosluzitelj) {
        this.emailPosluzitelj = emailPosluzitelj;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getOdabranaMapa() {
        return odabranaMapa;
    }

    public void setOdabranaMapa(String odabranaMapa) {
        this.odabranaMapa = odabranaMapa;
    }

    public List<String> getPopisMapa() {
        popisMapa = new ArrayList<String>();
        dohvatiMape();
        return popisMapa;
    }

    public void setPopisMapa(List<String> popisMapa) {
        this.popisMapa = popisMapa;
    }
    
    
    public List<Poruka> getPopisPoruka() {
        preuzmiPoruke(pocetak, stranicenje);
        setBrojPoruka(popisPoruka.size());
        return popisPoruka;
    }

    public void setPopisPoruka(List<Poruka> popisPoruka) {
        this.popisPoruka = popisPoruka;
    }

    public Poruka getOdabranaPoruka() {
        return odabranaPoruka;
    }

    public void setOdabranaPoruka(Poruka odabranaPoruka) {
        this.odabranaPoruka = odabranaPoruka;
    }

    public String getPorukaID() {
        return porukaID;
    }

    public void setPorukaID(String porukaID) {
        this.porukaID = porukaID;
    }

    public int getPocetak() {
        return pocetak;
    }

    public void setPocetak(int pocetak) {
        this.pocetak = pocetak;
    }

    public int getBrojPoruka() {
        return brojPoruka;
    }

    public void setBrojPoruka(int brojPoruka) {
        this.brojPoruka = brojPoruka;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public boolean isPrev() {
        return prev;
    }

    public void setPrev(boolean prev) {
        this.prev = prev;
    }

    public boolean isPraznaMapa() {
        return praznaMapa;
    }

    public void setPraznaMapa(boolean praznaMapa) {
        this.praznaMapa = praznaMapa;
    }
}
