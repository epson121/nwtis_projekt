
package org.foi.nwtis.lurajcevi.mb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.foi.nwtis.lurajcevi.RecordSerialization;
import org.foi.nwtis.lurajcevi.modeli.JMSPorukaMail;

/**
 * Message driven bean, dohvaća poruke iz Queue 1
 * @author Luka Rajcevic
 */
@MessageDriven(mappedName = "jms/NWTiS_lurajcevi_1", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MailMB implements MessageListener {
     public MailMB() {
    }
    
    @Override
    public void onMessage(Message message) {
        ObjectMessage objMsg = (ObjectMessage) message;
        try {
            JMSPorukaMail jmp = (JMSPorukaMail) objMsg.getObject();
            RecordSerialization.recordMail.add(jmp);
            System.out.println("MSG RECEIVED: ");
            System.out.println(jmp.getVrijemePocetka());
            System.out.println(jmp.getVrijemeZavrsetka());
        } catch (JMSException ex) {
            Logger.getLogger(MailMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
