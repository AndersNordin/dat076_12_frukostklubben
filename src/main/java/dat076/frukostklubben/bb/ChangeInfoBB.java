/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.model.User;
import dat076.frukostklubben.persistenceEJB.UserEJB;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
    private Boolean selected = false;
    //for authorizing changes:
    private String checkPasswd;
    private String newPasswd;
    //Is set when change info button is clicked:
    private String currentMail;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public String fetchUser() {
        user = userRegistry.find(currentMail);
        return "/users/changeInfo?faces-redirect=true";
    }

    public void change() {
        if (newPasswd != null) {
            user.setPasswd(newPasswd);
        }
        userRegistry.update(user);
    }

    public void correctPasswd(FacesContext context, UIComponent component,
            Object value) {
        String input = (String) value;
        if (!input.equals(user.getPasswd())) {
            ((EditableValueHolder) component).setValid(false);
            context.addMessage(component.getClientId(context), new FacesMessage(
                    " - Wrong password"));
        }
    }
    /*private String input1;
     private String input2;
     private boolean input1Set = false;

     public void pwValidator(FacesContext context, UIComponent component,
     Object value) {
     if (input1Set) {
     input2 = (String) value;
     if (input1.length() < 6) {
     ((EditableValueHolder) component).setValid(false);
     context.addMessage(component.getClientId(context), new FacesMessage(
     " - Password length > 6 chars"));
     } else if ((!input1.equals(input2))) {
     ((EditableValueHolder) component).setValid(false);
     context.addMessage(component.getClientId(context), new FacesMessage(
     " - Passwords do not match"));
     }
     } else {
     input1Set = true;
     input1 = (String) value;
     }
     }*/

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

    //For authorizing changes
    public String getCheckPasswd() {
        return checkPasswd;
    }

    public void setCheckPasswd(String checkPasswd) {
        this.checkPasswd = checkPasswd;
    }

    public void setNewPasswd(String newPasswd) {
        this.newPasswd = newPasswd;
    }

    public String getNewPasswd() {
        return newPasswd;
    }
}
