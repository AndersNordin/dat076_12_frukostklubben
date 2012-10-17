package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.UserEJB;
import dat076.frukostklubben.model.User;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Julia
 * Edited by: Anders, 2012-10-17
 */
@Named("signup")
@RequestScoped 
public class SignupBB {
    
    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    UserEJB userRegistry;    
    private User user = new User();    
    private String passwdConfirm;

    // ======================================
    // =           Public Methods           =
    // ======================================
    
    public String doCreateCustomer() {
        try {
            user = userRegistry.createUser(user);
            return "index.xhtml"; 
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bad Login name"));
            return null; // Stay on same page
        }
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
   
    public User getUser(){
        return this.user;
    }
    
    public void setUser(User user){
        this.user = user;
    }
    
    public void passwdConfirm(String passwdConfirm){
        this.passwdConfirm = passwdConfirm;
    }
    
    public String getpasswdConfirm(){
        return passwdConfirm;
    }
    
}
