
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
    
    /**
     * Dohvaćanje portflia od danog vlasnika
     * @param vlasnik
     * @return 
     */
    public List<LurajceviPortfolio> dohvatiPortfolie(String vlasnik){
        LurajceviKorisnici lk = lurajceviKorisniciFacade.dajKorisnika(vlasnik);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<LurajceviPortfolio> podaci = cq.from(LurajceviPortfolio.class);
        cq.select(podaci);
        cq.where(cb.equal(podaci.get("vlasnik"), lk));
        return em.createQuery(cq).getResultList();
    }
    
    /**
     * Dohvaćanje portfolia prema id-u
     * @param id
     * @return 
     */
    public LurajceviPortfolio dohvatiPortfolio(int id){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<LurajceviPortfolio> podaci = cq.from(LurajceviPortfolio.class);
        cq.select(podaci);
        cq.where(cb.equal(podaci.get("id"), id));
        return (LurajceviPortfolio) em.createQuery(cq).getResultList().get(0);
    }
    
    /**
     * Dodavanje novog portfolia u bazu
     * @param ime
     * @param vlasnik
     * @return
     * @throws NamingException 
     */
    public LurajceviPortfolio dodajPortfolio(String ime, String vlasnik) throws NamingException{
        LurajceviKorisnici lk = lurajceviKorisniciFacade.dajKorisnika(vlasnik);
        LurajceviPortfolio lp = new LurajceviPortfolio();
        lp.setNaziv(ime);
        lp.setVlasnik(lk);
        em.persist(lp);
        return lp;
    }
    
    /**
     * Dohvaćanje portfola prema nazivu portfolia
     * (ime je malo čudno, al neda mi se mijenjat, bilo je nekog refactoringa)
     * @param naziv
     * @return 
     */
    public LurajceviPortfolio dohvatiPortfolioPremaVlasnikuINazivu(String naziv){
        System.out.println("IME: " + naziv);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<LurajceviPortfolio> podaci = cq.from(LurajceviPortfolio.class);
        cq.select(podaci);
        cq.where(cb.equal(podaci.get("naziv"), naziv));
        System.out.println("Q: " + em.createQuery(cq).getResultList());
        if (em.createQuery(cq).getResultList() != null)
            return (LurajceviPortfolio) em.createQuery(cq).getResultList().get(0);
        else
            return null;
    }
    
    /**
     * TEST
     * @return 
     */
    public String test(){
        return "ASD2";
    }

}
