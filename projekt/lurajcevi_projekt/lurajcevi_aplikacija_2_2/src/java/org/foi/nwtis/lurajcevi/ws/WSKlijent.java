
package org.foi.nwtis.lurajcevi.ws;

/**
 * @document WSKlijent
 * @author Luka Rajcevic
 */
public class WSKlijent {
    
    /**
     * Pozivanje metode web servisa
     * Dohvaća sve aktivne zip kodove
     * @return listu zip kodova (String)
     */
    public static java.util.List<java.lang.String> dohvatiAktivneZipove() {
        org.foi.nwtis.lurajcevi.ws.WbServis_Service service = new org.foi.nwtis.lurajcevi.ws.WbServis_Service();
        org.foi.nwtis.lurajcevi.ws.WbServis port = service.getWbServisPort();
        return port.dohvatiAktivneZipove();
    }
    
    /**
     * Pozivanje metode web servisa
     * Dohvaća najnovije podatke o danom zip kodu
     * @param zip - zip kod
     * @return Podaci o zip kodu
     */
    public static MeteoPodaci dohvatiPodatkeOZipKodu(java.lang.String zip) {
        org.foi.nwtis.lurajcevi.ws.WbServis_Service service = new org.foi.nwtis.lurajcevi.ws.WbServis_Service();
        org.foi.nwtis.lurajcevi.ws.WbServis port = service.getWbServisPort();
        return port.dohvatiPodatkeOZipKodu(zip);
    }
    
    /**
     * Pozivanje metode web servisa
     * Dohvaća podatke u intervalu za dani zip kod
     * @param zip - zip kod
     * @param date1 - datum1
     * @param date2 - datum2
     * @return 
     */
    public static java.util.List<org.foi.nwtis.lurajcevi.ws.MeteoPodaci> dohvatiPodatkeInterval(java.lang.String zip, java.lang.String date1, java.lang.String date2) {
        org.foi.nwtis.lurajcevi.ws.WbServis_Service service = new org.foi.nwtis.lurajcevi.ws.WbServis_Service();
        org.foi.nwtis.lurajcevi.ws.WbServis port = service.getWbServisPort();
        return port.dohvatiPodatkeInterval(zip, date1, date2);
    }
    
    
    
}
