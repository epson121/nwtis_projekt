/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.ws;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviPortfolio;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviZP;
import org.foi.nwtis.lurajcevi.ejb.sb.LurajceviPortfolioFacade;
import org.foi.nwtis.lurajcevi.ejb.sb.LurajceviZPFacade;

/**
 * REST Web Service
 *
 * @author Luka Rajcevic
 */
@Path("/port")
@Stateless
public class PortfolioResourceContainer {
    @EJB
    private LurajceviZPFacade lurajceviZPFacade;
    @EJB
    private LurajceviPortfolioFacade lurajceviPortfolioFacade;
            
    private List<String> aktivniKorisnici = new ArrayList<String>();
    private List<LurajceviPortfolio> lp;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PortfolioResourceContainer
     */
    public PortfolioResourceContainer() {
       
    }
    

    /**
     * Retrieves representation of an instance of org.foi.nwtis.lurajcevi.ws.PortfolioResourceContainer
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of PortfolioResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("text/html")
    @Produces("text/html")
    public Response postHtml(String content) {
        //TODOString korisnik = (String) session.getAttribute("korisnik");
        return Response.created(context.getAbsolutePath()).build();
    }
    
    @Path("p/")
    @GET
    public String aktivniKorisnici(@Context HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        String s = "";
        if (session == null)
            return "PRAZNO1";
        if (session.getServletContext().getAttribute("aktivniKorisnici") == null){
            return "PRAZNO2";
        } else{
            aktivniKorisnici = (List<String>) session.getServletContext().getAttribute("aktivniKorisnici");
            for (Iterator<String> it = aktivniKorisnici.iterator(); it.hasNext();) {
                String p = it.next();
                s += p + "<br/>";
            }
            return s;
        }
    }
    
    @Path("p/{name}")
    @GET
    public String aktivniKorisnici(@PathParam("name") String name) {
        String s = "";
        if (aktivniKorisnici.contains(name)){
            lp = lurajceviPortfolioFacade.dohvatiPortfolie(name);
            for (LurajceviPortfolio l : lp){
                s += l.getNaziv() + "<br/>";
            }
        }
        return s;
    }
    
    @Path("p/{name}/{portfolio}")
    @GET
    public String popisZip(@PathParam("name") String name, @PathParam("portfolio") String portfolio) {
        String s = "";
        if (aktivniKorisnici.contains(name)){
            List<LurajceviZP> lzp = lurajceviZPFacade.dohvatiZP(lurajceviPortfolioFacade.dohvatiPortfolioPremaVlasnikuINazivu(portfolio));
            for (LurajceviZP l : lzp){
                s += l.getZipcode().getZip() +"<br/>";
            }
        }
        return s;
    }
    
    


   
}
