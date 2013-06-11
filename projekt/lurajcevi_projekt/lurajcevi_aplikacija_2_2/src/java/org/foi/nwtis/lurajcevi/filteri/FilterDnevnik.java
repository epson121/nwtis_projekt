/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.filteri;

import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.lurajcevi.ejb.sb.LurajceviDnevnikZahtjevaFacade;

/**
 *
 * @author Luka Rajcevic
 */
@WebFilter(filterName = "FilterDnevnik", urlPatterns = {"/*"})
@Stateless
public class FilterDnevnik implements Filter {
    @EJB
    private LurajceviDnevnikZahtjevaFacade lurajceviDnevnikZahtjevaFacade;
    
    
    
   @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         if (request instanceof HttpServletRequest){
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession sesija = ((HttpServletRequest)request).getSession();
            String zahtjev = ((HttpServletRequest) request).getRequestURI();
            Object korisnik = sesija.getAttribute("korisnik");                
            if (korisnik == null){
                lurajceviDnevnikZahtjevaFacade.dodajZahtjev(zahtjev, "anonymous");
            }
            else{
                lurajceviDnevnikZahtjevaFacade.dodajZahtjev(zahtjev, (String) korisnik);
            }
            chain.doFilter(request, response);
         }
    }

    @Override
    public void destroy() {

    }
    
}
