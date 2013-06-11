
package org.foi.nwtis.lurajcevi.filteri;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @document FilterZahtjeva
 * @author Luka Rajcevic
 */

@WebFilter("/faces/privatno/*")
public class FilterZahtjeva implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         if (request instanceof HttpServletRequest){
                HttpServletRequest req = (HttpServletRequest) request;
                HttpSession sesija = ((HttpServletRequest)request).getSession();
                String zahtjev = ((HttpServletRequest) request).getRequestURI();
                //TODO dodati u bazu
                Object korisnik = sesija.getAttribute("korisnik");                
                if (korisnik == null){
                    HttpServletResponse res = (HttpServletResponse) response;
                    res.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
                }
                else{
                    if ((zahtjev.contains("/emailPanel") || zahtjev.contains("/dnevnikZahtjeva")) && sesija.getAttribute("admin") == null){
                        RequestDispatcher rd = request.getRequestDispatcher("/faces/error.xhtml");
                        rd.forward(request, response);
                        return;
                    }
                    chain.doFilter(request, response);
                }
         }
    }

    @Override
    public void destroy() {

    }

}
