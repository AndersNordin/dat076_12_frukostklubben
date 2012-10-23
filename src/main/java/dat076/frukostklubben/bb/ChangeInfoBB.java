/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.UserEJB;
import dat076.frukostklubben.model.User;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author juliar
 */
@ManagedBean
@Named("changeInfo")
@SessionScoped
public class ChangeInfoBB implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    @EJB
    UserEJB userRegistry;
    private User user;
    private String currentMail;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public String fetchUser() {
        user = userRegistry.findByMail(currentMail);
        return "/users/changeInfo?faces-redirect=true";
    }

    public void change() {
        userRegistry.updateUser(user);
    }
    
    
    private String input1;
    private String input2;
    private boolean input1Set;
    
    public void pwValidator(FacesContext context, UIComponent component,
            Object value) {
        if (input1Set) {
            input2 = (String) value;
            if (input1.length() < 6) {
                ((EditableValueHolder) component).setValid(false);
                context.addMessage(component.getClientId(context), new FacesMessage(
                        " - Password length > 6 chars"));
            } else if ((!input1.equals(input2)) || input1 == null) {
                ((EditableValueHolder) component).setValid(false);
                context.addMessage(component.getClientId(context), new FacesMessage(
                        " - Passwords do not match"));
            }
        } else {
            input1Set = true;
            input1 = (String) value;
        }
    }

    // ======================================
    // =          Getters & Setters         =
    // ====================================== 
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //needed in order for setPropertyActionListener to work
    public String getCurrentMail() {
        return currentMail;
    }

    public void setCurrentMail(String currentMail) {
        this.currentMail = currentMail;
    }
}