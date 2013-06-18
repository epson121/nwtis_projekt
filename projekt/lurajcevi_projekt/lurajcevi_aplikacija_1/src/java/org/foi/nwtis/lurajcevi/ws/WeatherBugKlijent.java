
package org.foi.nwtis.lurajcevi.ws;

import net.wxbug.api.LiveWeatherData;
import net.wxbug.api.UnitType;
import org.foi.nwtis.lurajcevi.slusaci.SlusacAplikacije;

/**
 * @document WeatherBugKlijent
 * @author Luka Rajcevic
 */
public class WeatherBugKlijent {
    
    
    String wb_code = SlusacAplikacije.wb_key;
    
    public LiveWeatherData dajMeteoPodatke(String zip){
        return getLiveWeatherByUSZipCode(zip, UnitType.METRIC, wb_code);
    }
    
    private static LiveWeatherData getLiveWeatherByUSZipCode(java.lang.String zipCode, net.wxbug.api.UnitType unittype, java.lang.String aCode) {
        net.wxbug.api.WeatherBugWebServices service = new net.wxbug.api.WeatherBugWebServices();
        net.wxbug.api.WeatherBugWebServicesSoap port = service.getWeatherBugWebServicesSoap12();
        return port.getLiveWeatherByUSZipCode(zipCode, unittype, aCode);
    }

    
    
}
