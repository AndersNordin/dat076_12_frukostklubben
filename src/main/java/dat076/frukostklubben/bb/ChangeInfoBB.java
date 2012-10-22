/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.UserEJB;
import dat076.frukostklubben.model.User;
import java.io.Serializable;
import java.util.logging.Level;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author juliar
 */
@ManagedBean
@Named("changeInfo")
@SessionScoped
public class ChangeInfoBB implements Serializable{

    // ======================================
    // =             Attributes             =
    // ======================================
    @EJB
    UserEJB userRegistry;
    private User user;
    private String currentMail;

    public String fetchUser() {
        String result = "/users/changeInfo?faces-redirect=true";
        
        user = userRegistry.findByMail(currentMail);
        
        return result;
    }

    public User getUser() {
        return user;
    }

    public String getCurrentMail() {
        return currentMail;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCurrentMail(String currentMail) {
        this.currentMail = currentMail;
    }
}