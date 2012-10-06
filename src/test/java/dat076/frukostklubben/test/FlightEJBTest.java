package dat076.frukostklubben.test;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import dat076.frukostklubben.ejb.FlightEJB;
import dat076.frukostklubben.model.Flight;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
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
    private static FlightEJB flightEJB;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initContainer() throws Exception {
        //Create an Ejb container and put in an FlightEJB instance
        ec = EJBContainer.createEJBContainer();
        flightEJB = (FlightEJB)ec.getContext().
                lookup("java:global/classes/FlightEJB!dat076.frukostklubben.ejb.FlightEJB");
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
    public void testOfAllFlightEJBMethods() throws Exception {
        // Creates an instance of flight
        Flight flight = new Flight("Resa 1","Landvetter", "Frankfurt",new Date(Calendar.getInstance().getTimeInMillis()));
        
        // Persists the flight to the database
        flight = flightEJB.createFlight(flight);
        Assert.assertNotNull("ID should not be null", flight.getId());

        // Retrieves all the flights from the database
        List<Flight> flights = flightEJB.findFlights();
        Assert.assertNotNull(flights);
        
        //Gets a flight by its id
        Flight flight2 = flightEJB.findFlightById(flight.getId());
        Assert.assertTrue(flight.getId() == flight2.getId());
        
        
        //Updating the flight
        flight.setName("Ny Flygtur");
        flightEJB.updateFlight(flight);
        Flight flight3 = flightEJB.findFlightById(flight.getId());
        Assert.assertTrue(flight3.getName().equals("Ny Flygtur"));
        
                
        //If we delete the last flight the list of flights should på empty.
        flightEJB.deleteFlight(flight);
        flights = flightEJB.findFlights();
        Assert.assertTrue(flights.isEmpty());        
    }
}
