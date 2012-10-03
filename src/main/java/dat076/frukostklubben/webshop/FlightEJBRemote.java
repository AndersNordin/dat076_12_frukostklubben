/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.webshop;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author freein
 */
@Remote
public interface FlightEJBRemote {
    List<Flight> findFlights();
    Flight findFlightById(Long id);
    Flight createFlight(Flight flight);
    void deleteFlight(Flight flight);
    Flight updateFlight(Flight flight);

}
