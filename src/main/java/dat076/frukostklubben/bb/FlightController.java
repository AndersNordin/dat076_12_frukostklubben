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


/**
 *
 * @author freein
 */
@ManagedBean
@RequestScoped
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
        flightList = flightEJB.findFlights();
        return "VETTIG XHTML-SIDA";
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    
}
