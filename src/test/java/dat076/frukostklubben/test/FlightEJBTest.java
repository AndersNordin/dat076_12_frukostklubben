package dat076.frukostklubben.test;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import dat076.frukostklubben.persistenceEJB.FlightEJB;
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
        flight.setCost(2.3);
        
        // Persists the flight to the database
        flightEJB.create(flight);
        Assert.assertNotNull("ID should not be null", flight.getId());

        // Retrieves all the flights from the database
        List<Flight> flights = flightEJB.findAll();
        Assert.assertNotNull(flights);
        
        //Gets a flight by its id
        Flight flight2 = flightEJB.find(flight.getId());
        Assert.assertEquals(flight.getId(), flight2.getId());
        
        
        //Updating the flight
        flight.setName("Ny Flygtur");
        flightEJB.update(flight);
        Flight flight3 = flightEJB.find(flight.getId());
        Assert.assertEquals(flight3.getName(),"Ny Flygtur");
        
                
        //If we delete the last flight the list of flights should på empty.
        flightEJB.delete(flight);
        flights = flightEJB.findAll();
        Assert.assertTrue(flights.isEmpty());        
    }
}
