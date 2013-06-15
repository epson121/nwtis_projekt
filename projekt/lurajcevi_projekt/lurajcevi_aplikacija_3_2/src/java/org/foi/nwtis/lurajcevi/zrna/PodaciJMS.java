/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import org.foi.nwtis.lurajcevi.RecordSerialization;
import org.foi.nwtis.lurajcevi.modeli.JMSPorukaMail;
import org.foi.nwtis.lurajcevi.modeli.JMSPorukaZip;

/**
 *
 * @author Luka Rajcevic
 */
@Named(value = "podaciJMS")
@SessionScoped
public class PodaciJMS implements Serializable {

    List<JMSPorukaMail> podaciMail;
    List<JMSPorukaZip> podaciZip;
    JMSPorukaZip odabraniZip;
    JMSPorukaMail odabraniMail;
    
    public PodaciJMS() {
    }
    
    public String obrisiZip(){
        if (podaciZip.contains(odabraniZip)){
            podaciZip.remove(odabraniZip);
            RecordSerialization.recordZip.remove(odabraniZip);
        }
        return "";
    }
    
    public String obrisiMail(){
        if (podaciMail.contains(odabraniMail)){
            podaciMail.remove(odabraniMail);
            RecordSerialization.recordMail.remove(odabraniMail);
        }
        return "";
    }
    
    public String obrisiSveZip(){
        RecordSerialization.recordZip.clear();
        return "";
    }
    
    public String obrisiSveMail(){
        RecordSerialization.recordMail.clear();
        return "";
    }

    public List<JMSPorukaMail> getPodaciMail() {
        podaciMail = RecordSerialization.recordMail;
        return podaciMail;
    }

    public void setPodaciMail(List<JMSPorukaMail> podaciMail) {
        this.podaciMail = podaciMail;
    }

    public List<JMSPorukaZip> getPodaciZip() {
        podaciZip = RecordSerialization.recordZip;
        return podaciZip;
    }

    public void setPodaciZip(List<JMSPorukaZip> podaciZip) {
        this.podaciZip = podaciZip;
    }

    public JMSPorukaZip getOdabraniZip() {
        return odabraniZip;
    }

    public void setOdabraniZip(JMSPorukaZip odabraniZip) {
        this.odabraniZip = odabraniZip;
    }

    public JMSPorukaMail getOdabraniMail() {
        return odabraniMail;
    }

    public void setOdabraniMail(JMSPorukaMail odabraniMail) {
        this.odabraniMail = odabraniMail;
    }
    
    
}
