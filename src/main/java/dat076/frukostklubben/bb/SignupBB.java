/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.CustomerEJB;
import dat076.frukostklubben.model.Customer;
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

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    CustomerEJB customerRegistry;
    
    private Customer customer = new Customer();
    
    private String passwdConfirm;

    // ======================================
    // =           Public Methods           =
    // ======================================
    
    public String doCreateCustomer() {
        //log.log(Level.INFO, "New Customer Login {0} Passwd {1}", new Object[]{login, passwd});
        try {
            customer = customerRegistry.createCustomer(customer);
            return "index.xhtml"; //or some confirmation page
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Bad Login name"));
            return null; // Same page
        }
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
   
    public Customer getCustomer(){
        return this.customer;
    }
    
    public void setCutomer(Customer customer){
        this.customer = customer;
    }
    
    public void passwdConfirm(String passwdConfirm){
        this.passwdConfirm = passwdConfirm;
        //do something real...
    }
    
    public String getpasswdConfirm(){
        return passwdConfirm;
    }
    
}
