/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.FlightEJB;
import dat076.frukostklubben.model.Flight;
import java.util.ArrayList;
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
@Named("new")
public class FlightController {

    // ======================================
    // =             Attributes             =
    // ======================================

    @EJB
    private FlightEJB flightEJB;

    private Flight flight = new Flight();
    private List<Flight> flightList = new ArrayList<>();

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doCreateFlight() {
        flight = flightEJB.createFlight(flight);
        return "browse.xhtml";
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<Flight> getFlightList() {
        return flightEJB.findFlights();
    }
}
