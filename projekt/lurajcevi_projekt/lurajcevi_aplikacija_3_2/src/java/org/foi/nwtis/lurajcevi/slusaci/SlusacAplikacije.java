/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.slusaci;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.foi.nwtis.lurajcevi.RecordSerialization;
import org.foi.nwtis.lurajcevi.konfiguracije.Konfiguracija;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;
import org.foi.nwtis.lurajcevi.konfiguracije.bp.BP_Konfiguracija;

/**
 * Web application lifecycle listener.
 *
 * @author Luka Rajcevic
 */
@WebListener()
public class SlusacAplikacije implements ServletContextListener {
   public static Konfiguracija config = null;
    public static BP_Konfiguracija bpKonf = null;
    public static boolean stopped = false;
    public static boolean paused = false;
    public static String path = "";
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        path = sce.getServletContext().getRealPath("WEB-INF");
        String configFile = sce.getServletContext().getInitParameter("konfiguracija");
        String bpConfigFile = sce.getServletContext().getInitParameter("BPkonfiguracija");
        bpKonf = new BP_Konfiguracija(path + File.separator + bpConfigFile);
        
        try {
            config = KonfiguracijaApstraktna.preuzmiKonfiguraciju(path + File.separator + configFile);
        } catch (NemaKonfiguracije ex) {
            Logger.getLogger(SlusacAplikacije.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sce.getServletContext().setAttribute("BP_Konfiguracija", bpKonf);
        sce.getServletContext().setAttribute("Konfiguracija", config);
        
        System.out.println("Konfiguracija baze učitana.");
        System.out.println("Konfiguracija učitana.");
        System.out.println("Config file mail: " + config.dajPostavku("serijalizacija_datoteka_mail"));
        System.out.println("Config file zip: " + config.dajPostavku("serijalizacija_datoteka_zip"));
        
        RecordSerialization.deserializeJMSMail(config.dajPostavku("serijalizacija_datoteka_mail"));
        RecordSerialization.deserializeJMSZip(config.dajPostavku("serijalizacija_datoteka_zip"));
       
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        RecordSerialization.serializeToFileMail(config.dajPostavku("serijalizacija_datoteka_mail"), RecordSerialization.recordMail);
        RecordSerialization.serializeToFileZip(config.dajPostavku("serijalizacija_datoteka_zip"), RecordSerialization.recordZip);
    }
}
