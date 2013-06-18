
package org.foi.nwtis.lurajcevi.ejb.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import org.foi.nwtis.lurajcevi.modeli.JMSPorukaMail;

/**
 *
 * @author Luka Rajcevic
 */
@Stateless
public class MailJMS {
    
    public void sendJMSMessageToNWTiS_lurajcevi_1(String v1, String v2, int n1, int n2 ) throws JMSException {
        Session session = null;
        Connection connection = null;
        try {
            InitialContext ic = new InitialContext();
            ConnectionFactory cf = (ConnectionFactory)ic.lookup("jms/NWTiS_QF_lurajcevi_1");
            Queue orderQueue = (Queue)ic.lookup("jms/NWTiS_lurajcevi_1");
            connection = cf.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(orderQueue);
            
            connection.start();
            ObjectMessage message = session.createObjectMessage();
            JMSPorukaMail jmsp = new JMSPorukaMail(v1, v2, n1+"", n2+"");
            message.setObject(jmsp);
            producer.send(message);
            producer.close();
            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    

}
