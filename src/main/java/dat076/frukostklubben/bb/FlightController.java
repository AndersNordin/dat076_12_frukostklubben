/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;


import dat076.frukostklubben.ejb.ShoppingCartEJB;
import dat076.frukostklubben.model.Flight;
import dat076.frukostklubben.persistenceEJB.FlightEJB;
import java.util.List;
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
@Named("flightController")
public class FlightController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private FlightEJB flightEJB;
    @EJB
    private ShoppingCartEJB cart;
    private Flight flight = new Flight();

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doCreateFlight() {
        flightEJB.create(flight);
        return "/browse?faces-redirect=true"; // bug fix, need parameter to redirect correctly
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================
    public Flight getFlight(){
        return this.flight;
    }
    
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<Flight> getFlightList() {
        return flightEJB.findAll();
    }
    
    public void buy(){
        cart.addFlight(flight);
    }
}
