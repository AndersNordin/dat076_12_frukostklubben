/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.ShoppingCartEJB;
import dat076.frukostklubben.model.User;
import dat076.frukostklubben.persistenceEJB.UserEJB;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author freein
 */
@Named("orderBB")
@RequestScoped
public class OrderBB {
    private String mail;
    @EJB
    private ShoppingCartEJB shoppingCartEJB;
    @EJB
    private UserEJB userEJB;
    
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    

}
