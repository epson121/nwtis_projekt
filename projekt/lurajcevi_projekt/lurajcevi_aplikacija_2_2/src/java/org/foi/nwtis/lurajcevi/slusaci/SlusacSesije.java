/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.slusaci;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Web application lifecycle listener.
 *
 * @author Luka Rajcevic
 */
@WebListener()
public class SlusacSesije implements HttpSessionAttributeListener {
    
    /**
     * Poziva se prilikom dodavanja atributa u sesiju
     * Služi za praćenje svih aktivnih korisnika
     * @param event 
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getName().compareTo("korisnik") == 0){
            String user = (String) event.getValue();
            List<String> aktivniKorisnici = (List<String>) event.getSession().getServletContext().getAttribute("aktivniKorisnici");
            if (aktivniKorisnici == null){
                aktivniKorisnici = new ArrayList<String>();
            }
            aktivniKorisnici.add(user);
            event.getSession().getServletContext().setAttribute("aktivniKorisnici", aktivniKorisnici);
        }
    }
    
    /**
     * Poziva se prilikom brisanja atributa iz sesije
     * Sluzi za brisanje korisnika iz liste aktivnih
     * @param event 
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
         if (event.getName().compareTo("korisnik") == 0){
            String korisnik = (String) event.getValue();
            List<String> aktivniKorisnici = (List<String>) event.getSession().getServletContext().getAttribute("aktivniKorisnici");
            aktivniKorisnici.remove(korisnik);
            event.getSession().getServletContext().setAttribute("aktivniKorisnici", aktivniKorisnici);
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        
    }
}
