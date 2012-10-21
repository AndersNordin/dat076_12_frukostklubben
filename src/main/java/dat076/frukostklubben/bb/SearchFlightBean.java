/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.FlightEJB;
import dat076.frukostklubben.model.Flight;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Johan
 */
@Named("search")
@RequestScoped
public class SearchFlightBean implements Serializable {
    @EJB
    private FlightEJB flightEJB;
    
    private Flight flight = new Flight();
    private List<Flight> flights;


    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public String search(){
        flights = flightEJB.searchFlights(this.flight);
        //return "results"; //Haha, pikärt läge. //Denna gör så att min sökfunktion inte längre fungerar.
        return "results"; // bug fix, need parameter to redirect correctly
    }   
}