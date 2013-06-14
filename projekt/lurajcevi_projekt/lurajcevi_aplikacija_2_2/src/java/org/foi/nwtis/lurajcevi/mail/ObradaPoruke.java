
package org.foi.nwtis.lurajcevi.mail;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import org.foi.nwtis.lurajcevi.ejb.jms.MailJMS;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;

/**
 * @document ObradaPoruke
 * @author Luka Rajcevic
 */
@Stateless
public class ObradaPoruke extends Thread {

    @EJB
    private MailJMS mailJMS;

    private Konfiguracija config;
    
    private String emailPosluzitelj;
    private int emailPort;
    private String korisnickoIme;
    private String korisnickaLozinka;
    private String trazeniPredmet;
    private String nwtisFolder;
    private String ne_nwtisFolder;
    
    private int brojProcitanihPoruka = 0;
    private int brojNwtisPoruka = 0;
    private int interval;
    
    public ObradaPoruke() {
    }
    
    /**
     * Konstruktor
     * @param config  - referenca na datoteku konfiguracije
     */
    public ObradaPoruke(Konfiguracija config) {
        this.config = config;
        emailPosluzitelj = config.dajPostavku("emailPosluzitelj");
        emailPort = Integer.parseInt(config.dajPostavku("emailPort"));
        korisnickoIme = config.dajPostavku("username");
        korisnickaLozinka = config.dajPostavku("password");
        trazeniPredmet = config.dajPostavku("trazeniPredmet");
        interval = Integer.parseInt(config.dajPostavku("interval"));
        nwtisFolder = config.dajPostavku("nwtisFolder");
        ne_nwtisFolder = config.dajPostavku("ne_nwtisFolder");
    }
    
    @Override
    public synchronized void start() {
        super.start();
    }
    
    /**
     * Run metoda dretve koja u pravilnom intervalu provjerava e mail sandučić,
     * sortira poruke, šalje JMS poruku..
     */
    @Override
    public void run() {
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
        long start, duration = 0;
        while (true) {
                try {
                    start = System.currentTimeMillis();
                    session = Session.getDefaultInstance(System.getProperties(), null);
                    store = session.getStore("imap");
                    store.connect(emailPosluzitelj, emailPort, korisnickoIme, korisnickaLozinka);
                    printData("Connection established with IMAP server.");
                    
                    folder = store.getDefaultFolder();
                    folder = folder.getFolder("inbox");
                    folder.open(Folder.READ_WRITE);
                    messages = folder.getMessages();
                    brojProcitanihPoruka = messages.length;
                    System.out.println("BROJ PROCITANIH PORUKA: " + brojProcitanihPoruka);
                    for (int messageNumber = 0; messageNumber < messages.length; messageNumber++) {
                        
                        message = messages[messageNumber];
                        messagecontentObject = message.getContent();
                        
                        if (message.getSubject().startsWith(trazeniPredmet)){
                            System.out.println("Trazena poruka.");
                            contentType = message.getContentType();
                            
                            if (contentType.startsWith("text/plain") || contentType.startsWith("TEXT/PLAIN")){
                                brojNwtisPoruka++;
                                Folder f = store.getFolder(nwtisFolder);
                                if (!f.exists()){ 
                                    f.create(Folder.HOLDS_MESSAGES);
                                }
                                f.appendMessages(new Message[] {message});
                                if (f.isOpen())
                                    f.close(true);
                                message.setFlag(Flags.Flag.DELETED, true);
                                continue;
                            }
                           
                        } else {
                            System.out.println("Nevaljana poruka");
                            Folder f = store.getFolder(ne_nwtisFolder);
                            if (!f.exists()){ 
                                f.create(Folder.HOLDS_MESSAGES);
                            }
                            f.appendMessages(new Message[] {message});
                            if (f.isOpen())
                                f.close(true);
                            message.setFlag(Flags.Flag.DELETED, true);
                            continue;
                        }
                    }  
                    duration = System.currentTimeMillis() - start;
                    System.out.println("SALJEM JMS.");
                    mailJMS = new MailJMS();
                    mailJMS.sendJMSMessageToNWTiS_lurajcevi_1(new Date(start) + "","" + new Date(start + duration), brojProcitanihPoruka, brojNwtisPoruka);
                    brojProcitanihPoruka = 0;
                    brojNwtisPoruka = 0;
                    try {
                        long sleepTime;
                        sleepTime = ((int) (interval * 1000) - duration);
                        System.out.println("Spavanje: " + sleepTime);
                        if (sleepTime > 0) {
                            sleep(sleepTime);
                        }
                        else {
                            sleep(0);
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(ObradaPoruke.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
            } catch (NoSuchProviderException ex) {
                    Logger.getLogger(ObradaPoruke.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(ObradaPoruke.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ObradaPoruke.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JMSException ex) {
                Logger.getLogger(ObradaPoruke.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Metoda koja ispisuje string dobiven u parametru na standardni izlaz
     * @param data string koji se ispisuje
     */
    private void printData(String data) {
        System.out.println(data);
    }

   
}
