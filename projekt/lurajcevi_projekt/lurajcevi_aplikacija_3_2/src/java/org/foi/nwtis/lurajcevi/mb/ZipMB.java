/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lurajcevi.mb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.foi.nwtis.lurajcevi.RecordSerialization;
import org.foi.nwtis.lurajcevi.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.lurajcevi.konfiguracije.NemaKonfiguracije;
import org.foi.nwtis.lurajcevi.modeli.JMSPorukaZip;
import org.foi.nwtis.lurajcevi.slusaci.SlusacAplikacije;

/**
 * Message driven bean, prima poruke iz Queue2
 * @author Luka Rajcevic
 */
@MessageDriven(mappedName = "jms/NWTiS_lurajcevi_2", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ZipMB implements MessageListener {
    
    public ZipMB() {
    }
    
    @Override
    public void onMessage(Message message) {
        ObjectMessage objMsg = (ObjectMessage) message;
        try {
            JMSPorukaZip jmz = (JMSPorukaZip) objMsg.getObject();
            RecordSerialization.recordZip.add(jmz);
            String zip = jmz.getZip();
            System.out.println("Dosao je JMS za zip: " + jmz.getZip());
            String s = sendRequest("USER admin; PASSWD admin; TEST ZIP " + zip + ";");
            if (s.trim().equals("OK 44")){
                sendRequest("USER admin; PASSWD admin; ADD ZIP " + zip + ";");
            }
        } catch (JMSException ex) {
            Logger.getLogger(MailMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String sendRequest(String request){
        StringBuilder response = new StringBuilder();
        try {
            InputStream is = null;
            OutputStream os = null;
            Socket server = null;
            int character;
            System.out.println("REQ: " + request);
            String serverIP = KonfiguracijaApstraktna.preuzmiKonfiguraciju(SlusacAplikacije.path + File.separator + "lurajcevi_projekt_config.xml").dajPostavku("server_ip");
            int port = Integer.parseInt(KonfiguracijaApstraktna.preuzmiKonfiguraciju(SlusacAplikacije.path + File.separator + "lurajcevi_projekt_config.xml").dajPostavku("port"));
                try{
                    server = new Socket(serverIP, port);
                    os = server.getOutputStream();
                    is = server.getInputStream();
                    os.write(request.getBytes());
                    os.flush();
                    server.shutdownOutput();
                    
                    while ((character = is.read()) != -1){
                        response.append((char) character);
                    }
                }
                catch (IOException ex) {
                    System.out.println("ERROR: server is not responding.");
                    ex.printStackTrace();
                }
                finally{
                    try{
                        if (os != null)
                            os.close();
                        if (is != null)
                            is.close();
                        if (server != null)
                            server.close();
                    }
                    catch (IOException ex){
                    }
                } 
        } catch (NemaKonfiguracije ex) {
            ex.printStackTrace();
        }
        System.out.println("REQUEST: " + request);
        System.out.println("RESPONSE: " + response);
        return response.toString();
    }
    
}
