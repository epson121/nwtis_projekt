
package org.foi.nwtis.lurajcevi.ejb.sb;

import java.util.List;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviPortfolio;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviZP;
import org.foi.nwtis.lurajcevi.ejb.eb.ZipCodes;

/**
 * @document LurajceviZPFacade
 * @author Luka Rajcevic
 */
@Stateless
public class LurajceviZPFacade extends AbstractFacade<LurajceviZP> {
    @PersistenceContext(unitName = "lurajcevi_aplikacija_2_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LurajceviZPFacade() {
        super(LurajceviZP.class);
    }
    
    public void dodajZP(LurajceviPortfolio lp, int zip) throws NamingException{
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<ZipCodes> zipovi = cq.from(ZipCodes.class);
        cq.select(zipovi);
        cq.where(cb.equal(zipovi.get("zip"), zip));
        ZipCodes zc =  (ZipCodes) em.createQuery(cq).getResultList().get(0);
        
        LurajceviZP lzp = new LurajceviZP();
        lzp.setPortfolio(lp);
        lzp.setZipcode(zc);
        em.persist(lzp);
        
    }
    
    public List<LurajceviZP> dohvatiZP(LurajceviPortfolio lp){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<LurajceviZP> podaci = cq.from(LurajceviZP.class);
        cq.select(podaci);
        cq.where(cb.equal(podaci.get("portfolio"), lp));
        if ( em.createQuery(cq).getResultList() == null)
            return null;
        else
            return (List<LurajceviZP>) em.createQuery(cq).getResultList();
    }
    
}
