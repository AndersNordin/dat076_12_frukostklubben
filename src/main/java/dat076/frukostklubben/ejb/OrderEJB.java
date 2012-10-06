/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.ejb;

import dat076.frukostklubben.model.Customer;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Fredrik
 */

@Stateless
public class OrderEJB {

    @Asynchronous
    public void sendEmailOrderComplete() {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "Grattis till köpet";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@frukostklubben.com", "Frukostklubben.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("Einarsson.F@gmail.com", "Mr. User"));
            msg.setSubject("Frukostklubbem");
            msg.setText(msgBody);
            Transport.send(msg);

        } 
        catch (AddressException e) {
            // ...
        } 
        catch (MessagingException | UnsupportedEncodingException e) {
            // ...
        }
    }
}

