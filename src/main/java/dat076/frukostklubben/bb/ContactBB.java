/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.MailEJB;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author freein
 */
@ManagedBean
@RequestScoped
@Named("contactBB")
public class ContactBB {
    @EJB
    MailEJB mailEJB;
    
    private String name;
    private String mail;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getComment() {
        return message;
    }

    public void setComment(String comment) {
        this.message = comment;
    }
    
    public String submit(){
        mailEJB.sendEmailOrderComplete(name, mail, message);
        return "index?faces-redirect=true"; // bug fix, need parameter to redirect correctly
    }
}
