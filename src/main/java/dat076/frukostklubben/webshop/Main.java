/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.webshop;

import javax.ejb.EJB;

/**
 *
 * @author freein
 */
public class Main {
    @EJB
    private static FlightEJBRemote flightEJB;
    public static void main(String[] args) {
        Flight flight = new Flight();
        flight.setName("First flight ever");
        flightEJB.createFlight(flight);
        flight.setFromAirport("Landvetter");
        flightEJB.updateFlight(flight);
        flightEJB.deleteFlight(flight);
    }
}

