/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.persistenceEJB.UserEJB;
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
    private Boolean selected = false;
    private String checkPasswd;
    
    //Attributes that can be changed
    private String newPasswd;
    

    // ======================================
    // =           Public Methods           =
    // ======================================
    public String fetchUser() {
        user = userRegistry.find(currentMail);
        return "/users/changeInfo?faces-redirect=true";
    }

    public void change() {
        if (checkPasswd.equals(user.getPasswd())) {
            user.setPasswd(newPasswd);
            userRegistry.update(user);
        } else {
            checkPasswd = "";
        }
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

    //For checkbox
    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    //For checking the password
    public String getCheckPasswd() {
        return checkPasswd;
    }

    public void setCheckPasswd(String checkPasswd) {
        this.checkPasswd = checkPasswd;
    }

    //Attributes that can be changed
    public String getNewPasswd() {
        return newPasswd;
    }

    public void setNewPasswd(String newPasswd) {
        this.newPasswd = newPasswd;
    }
}