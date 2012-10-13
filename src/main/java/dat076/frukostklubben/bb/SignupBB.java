/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.CustomerEJB;
import dat076.frukostklubben.model.Address;
import dat076.frukostklubben.model.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Julia
 */
@Named("signup")
@RequestScoped //created once for every request
public class SignupBB {

    @NotNull
    private String userName;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String mail;
    @NotNull
    private String street;
    @NotNull
    private Integer zip;
    @NotNull
    private String city;
    @NotNull
    private String passwd;
    @EJB
    CustomerEJB customerRegistry;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getStreet() {
        return street;
    }

    public Integer getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }
    
    public String getPasswd(){
        return passwd;
    }

    public String action() {
        //log.log(Level.INFO, "New Customer Login {0} Passwd {1}", new Object[]{login, passwd});
        try {
            Address address = new Address(); //fix new constructor 
            customerRegistry.createCustomer(new Customer(firstName, lastName, mail, address));
            return "index";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bad Login name"));
            return null; // Same page
        }
    }
}
