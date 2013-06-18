/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.lurajcevi.ServerKomanda;
import org.foi.nwtis.lurajcevi.db.DBConnector;
import org.foi.nwtis.lurajcevi.ws.MeteoPodaci;

/**
 *
 * @author Luka Rajcevic
 */

public class Kontroler extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String zahtjev = request.getServletPath();
        String odrediste = null;
        if (zahtjev.equals("/Kontroler"))
            odrediste = "/index.jsp";
        
        else if (zahtjev.equals("/PrijavaKorisnika")) 
            odrediste = "/login.jsp";
        
        else if (zahtjev.equals("/OdjavaKorisnika")){
            HttpSession sesija = request.getSession();
            sesija.removeAttribute("korisnik");
            odrediste = "/Kontroler";
        }
        
        else if (zahtjev.equals("/ProvjeraKorisnika")) {
            String korIme = request.getParameter("kor_ime");
            String lozinka = request.getParameter("lozinka");
            HttpSession sesija = null;
            if (korIme == null || korIme.trim().length() == 0 || lozinka == null 
                || lozinka.trim().length() == 0){
                odrediste = "/PrijavaKorisnika";
            } 
            else {
                boolean b = false;
                try {
                    b = DBConnector.provjeriKorisnika("lurajcevi_admin_podaci", korIme, lozinka);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (b){
                    sesija = request.getSession();
                    sesija.setAttribute("korisnik", korIme);
                    odrediste = "/Kontroler";
                } else{
                    odrediste = "/PrijavaKorisnika";
                }
                
            }
        } 
        
        else if (zahtjev.equals("/PregledMeteoPodataka")){
            HttpSession sesija = request.getSession();
            int brojStranica = 5;
            if (sesija.getAttribute("broj_stranica") == null)
                sesija.setAttribute("broj_stranica", brojStranica);
            try {
                if (sesija.getAttribute("meteo_podaci") == null){
                    List<MeteoPodaci> podaci = DBConnector.dohvatiMeteoPodatke();
                    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
                     
                    for (MeteoPodaci mp : podaci){
                        String d = df.format(df2.parse(mp.getDatum()));
                        mp.setDatum(d);
                    }
                    sesija.setAttribute("meteo_podaci", podaci);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
            odrediste = "/jsp/pregledMeteoPodataka.jsp";
        } 
        
        else if (zahtjev.equals("/PregledDnevnikaSocketServera")){
            HttpSession sesija = request.getSession();
            List<ServerKomanda> pregled_zahtjeva = null;
            try {
                pregled_zahtjeva = DBConnector.dohvatiPopisSocketServerKomandi("lurajcevi_dnevnik_servera", 0, "");
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

                for (ServerKomanda sk : pregled_zahtjeva){
                    String d = df.format(df2.parse(sk.getDatum()));
                    sk.setDatum(d);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
            sesija.setAttribute("dnevnik_socket_servera", pregled_zahtjeva);
            odrediste = "/jsp/pregledDnevnikaSocketServera.jsp";
        } 
        
        else if (zahtjev.equals("/PregledDnevnikaSocketServeraFilter")){
            HttpSession sesija = request.getSession();
            String status = request.getParameter("status");
            List<ServerKomanda> pregled_zahtjeva = null;
            try {
                pregled_zahtjeva = DBConnector.dohvatiPopisSocketServerKomandi("lurajcevi_dnevnik_servera", 1, status);
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");

                for (ServerKomanda sk : pregled_zahtjeva){
                    String d = df.format(df2.parse(sk.getDatum()));
                    sk.setDatum(d);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
            sesija.setAttribute("dnevnik_socket_servera", pregled_zahtjeva);
            odrediste = "/jsp/pregledDnevnikaSocketServera.jsp";
        }
        
        else if (zahtjev.equals("/PregledZahtjevaServera")){
            HttpSession sesija = request.getSession();
            List<HttpZahtjev> pregled_zahtjeva = null;
            try {
                pregled_zahtjeva = DBConnector.dohvatiPopisZahtjevaPremaServeru("lurajcevi_dnevnik_zahtjeva");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
            sesija.setAttribute("pregled_zahtjeva", pregled_zahtjeva);
            odrediste = "/jsp/pregledZahtjevaServera.jsp";
        } 
        
        else if (zahtjev.equals("/MeteoPodaciStranicenje")){
            int brojStranica = Integer.parseInt(request.getParameter("broj_stranica"));
            HttpSession sesija = request.getSession();
            sesija.setAttribute("broj_stranica", brojStranica);
            odrediste = "/jsp/pregledMeteoPodataka.jsp";
        }  
        
        else if (zahtjev.equals("/MeteoPodaciZipFilter")){
            
            HttpSession sesija = request.getSession();
            String zip = request.getParameter("zip");
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
             if (zip != null && !zip.trim().equals("")){
                List<MeteoPodaci> podaci = null;
                try {
                    podaci = DBConnector.dohvatiNajnovijePodatke("lurajcevi_podaci_zip", zip, 0, 1);
                    for (MeteoPodaci mp : podaci){
                        String d = df.format(df2.parse(mp.getDatum()));
                        mp.setDatum(d);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
                sesija.setAttribute("meteo_podaci", podaci);
             } else {
                 try{
                    List<MeteoPodaci> podaci = DBConnector.dohvatiMeteoPodatke();
                    
                     
                    for (MeteoPodaci mp : podaci){
                        String d = df.format(df2.parse(mp.getDatum()));
                        mp.setDatum(d);
                    }
                    sesija.setAttribute("meteo_podaci", podaci);
                 } catch (ParseException pe){
                     pe.printStackTrace();
                 } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
             odrediste = "/jsp/pregledMeteoPodataka.jsp";
        }
        
        else if (zahtjev.equals("/MeteoPodaciInterval")){
            HttpSession sesija = request.getSession();
            String zip = request.getParameter("zip");
            String datum1 = request.getParameter("datum1");
            String datum2 = request.getParameter("datum2");
            if (zip != null && !zip.trim().equals("") && 
                datum1 != null && !datum1.trim().equals("") &&
                datum2 != null && !datum2.trim().equals("")){
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");//02.06.2013 18:10:47
                try{
                    String dat1 = df2.format(df.parse(datum1));
                    String dat2 = df2.format(df.parse(datum2));
                    List<MeteoPodaci> podaci = DBConnector.dohvatiPodatkeInterval("lurajcevi_podaci_zip", zip, dat1, dat2);
                     
                    for (MeteoPodaci mp : podaci){
                        String d = df.format(df2.parse(mp.getDatum()));
                        mp.setDatum(d);
                    }
                    sesija.setAttribute("meteo_podaci", podaci);
                    
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException e){  
                    e.printStackTrace();
                }
            }
            odrediste = "/jsp/pregledMeteoPodataka.jsp";
        }
        
        else{
            ServletException up = new ServletException("Zahtjev nije poznat");
            throw up;
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(odrediste);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
