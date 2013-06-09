
package org.foi.nwtis.lurajcevi.ejb.eb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @document LurajceviDnevnikZahtjeva
 * @author Luka Rajcevic
 */
@Entity
@Table(name = "LURAJCEVI_DNEVNIK_ZAHTJEVA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LurajceviDnevnikZahtjeva.findAll", query = "SELECT l FROM LurajceviDnevnikZahtjeva l"),
    @NamedQuery(name = "LurajceviDnevnikZahtjeva.findById", query = "SELECT l FROM LurajceviDnevnikZahtjeva l WHERE l.id = :id"),
    @NamedQuery(name = "LurajceviDnevnikZahtjeva.findByZahtjev", query = "SELECT l FROM LurajceviDnevnikZahtjeva l WHERE l.zahtjev = :zahtjev"),
    @NamedQuery(name = "LurajceviDnevnikZahtjeva.findByKorisnik", query = "SELECT l FROM LurajceviDnevnikZahtjeva l WHERE l.korisnik = :korisnik"),
    @NamedQuery(name = "LurajceviDnevnikZahtjeva.findByVrijeme", query = "SELECT l FROM LurajceviDnevnikZahtjeva l WHERE l.vrijeme = :vrijeme")})
public class LurajceviDnevnikZahtjeva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 255)
    @Column(name = "ZAHTJEV")
    private String zahtjev;
    @Size(max = 50)
    @Column(name = "KORISNIK")
    private String korisnik;
    @Column(name = "VRIJEME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vrijeme;

    public LurajceviDnevnikZahtjeva() {
    }

    public LurajceviDnevnikZahtjeva(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZahtjev() {
        return zahtjev;
    }

    public void setZahtjev(String zahtjev) {
        this.zahtjev = zahtjev;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public Date getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(Date vrijeme) {
        this.vrijeme = vrijeme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LurajceviDnevnikZahtjeva)) {
            return false;
        }
        LurajceviDnevnikZahtjeva other = (LurajceviDnevnikZahtjeva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.foi.nwtis.lurajcevi.ejb.eb.LurajceviDnevnikZahtjeva[ id=" + id + " ]";
    }

}
