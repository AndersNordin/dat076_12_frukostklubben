package dat076.frukostklubben.test;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import dat076.frukostklubben.webshop.Flight;
import dat076.frukostklubben.webshop.FlightEJB;
import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author freein
 */
public class FlightEJBTest {

    // ======================================
    // =             Fields             =
    // ======================================
    private static EJBContainer ec;
    private static Context ctx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initContainer() throws Exception {
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (ec != null){
            ec.close();
        }
    }

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test
    public void shouldCreateAFlight() throws Exception {
        ec = javax.ejb.embeddable.EJBContainer.createEJBContainer();

        FlightEJB flightEJB = (FlightEJB)ec.getContext().
                lookup("java:global/classes/FlightEJB!dat076.frukostklubben.webshop.FlightEJB");
        
        // Creates an instance of flight
        Flight flight = new Flight("Resa 1","Landvetter", "Frankfurt",new Date());
        

        // Persists the flight to the database
        flight = flightEJB.createFlight(flight);
        Assert.assertNotNull("ID should not be null", flight.getId());

        // Retrieves all the flights from the database
        List<Flight> flights = flightEJB.findFlights();
        Assert.assertNotNull(flights);
    }
}
