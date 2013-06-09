
package org.foi.nwtis.lurajcevi.ejb.sb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.foi.nwtis.lurajcevi.ejb.eb.LurajceviDnevnikZahtjeva;

/**
 * @document LurajceviDnevnikZahtjevaFacade
 * @author Luka Rajcevic
 */
@Stateless
public class LurajceviDnevnikZahtjevaFacade extends AbstractFacade<LurajceviDnevnikZahtjeva> {
    @PersistenceContext(unitName = "lurajcevi_aplikacija_2_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LurajceviDnevnikZahtjevaFacade() {
        super(LurajceviDnevnikZahtjeva.class);
    }
    
    public void dodajZahtjev(String zahtjev, String korisnik) {
        System.out.println("ZAH: " + zahtjev);
        System.out.println("KOR: " + korisnik);
        LurajceviDnevnikZahtjeva ldz = new LurajceviDnevnikZahtjeva();
        ldz.setZahtjev(zahtjev);
        ldz.setKorisnik(korisnik);
        ldz.setVrijeme(new Date());
        em.persist(ldz);
    }
    
    /**
     *
     * @return
     */
    public List<LurajceviDnevnikZahtjeva> dohvatiZahtjeve(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<LurajceviDnevnikZahtjeva> podaci = cq.from(LurajceviDnevnikZahtjeva.class);
        cq.select(podaci);
        return em.createQuery(cq).getResultList();
    }
    
     public List<LurajceviDnevnikZahtjeva> dohvatiZahtjeveFiltrirano(Date d1, Date d2){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<LurajceviDnevnikZahtjeva> podaci = cq.from(LurajceviDnevnikZahtjeva.class);
        cq.select(podaci);
        cq.where(cb.and(cb.between(podaci.<Date>get("vrijeme"), d1, d2)));
        return em.createQuery(cq).getResultList();
    }
     
    public List<LurajceviDnevnikZahtjeva> dohvatiFiltriranoPoKorisniku(String korisnik){
       CriteriaBuilder cb = em.getCriteriaBuilder();
       CriteriaQuery cq = cb.createQuery();
       Root<LurajceviDnevnikZahtjeva> podaci = cq.from(LurajceviDnevnikZahtjeva.class);
       cq.select(podaci);
       cq.where(cb.like(podaci.<String>get("korisnik"), korisnik + "%"));
       return em.createQuery(cq).getResultList();
    }
     
}
