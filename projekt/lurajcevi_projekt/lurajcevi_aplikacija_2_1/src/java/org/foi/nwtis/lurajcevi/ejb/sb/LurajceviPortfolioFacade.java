
package org.foi.nwtis.lurajcevi.ejb.sb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviKorisnici;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviPortfolio;


/**
 * @document LurajceviPortfolioFacade
 * @author Luka Rajcevic
 */
@Stateless
public class LurajceviPortfolioFacade extends AbstractFacade<LurajceviPortfolio> {
    @EJB
    private LurajceviKorisniciFacade lurajceviKorisniciFacade;
    @PersistenceContext(unitName = "lurajcevi_aplikacija_2_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LurajceviPortfolioFacade() {
        super(LurajceviPortfolio.class);
    }
    
    public List<LurajceviPortfolio> dohvatiPortfolie(String vlasnik){
        LurajceviKorisnici lk = lurajceviKorisniciFacade.dajKorisnika(vlasnik);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<LurajceviPortfolio> podaci = cq.from(LurajceviPortfolio.class);
        cq.select(podaci);
        cq.where(cb.equal(podaci.get("vlasnik"), lk));
        return em.createQuery(cq).getResultList();
    }
    
    public LurajceviPortfolio dohvatiPortfolio(int id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<LurajceviPortfolio> podaci = cq.from(LurajceviPortfolio.class);
        cq.select(podaci);
        cq.where(cb.equal(podaci.get("id"), id));
        return (LurajceviPortfolio) em.createQuery(cq).getResultList().get(0);
    }
    
    public LurajceviPortfolio dodajPortfolio(String ime, String vlasnik) throws NamingException{
        LurajceviKorisnici lk = lurajceviKorisniciFacade.dajKorisnika(vlasnik);
        LurajceviPortfolio lp = new LurajceviPortfolio();
        lp.setNaziv(ime);
        lp.setVlasnik(lk);
        em.persist(lp);
        return lp;
    }
    
}
