/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.test;

import dat076.frukostklubben.ejb.CustomerEJB;
import dat076.frukostklubben.ejb.FlightEJB;
import dat076.frukostklubben.model.Address;
import dat076.frukostklubben.model.Customer;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author freein
 */
public class CustomerEJBTest {
        // ======================================
    // =             Fields             =
    // ======================================
    private static EJBContainer ec;
    private static CustomerEJB customerEJB;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initContainer() throws Exception {
        //Create an Ejb container and put in an CustomerEJB instance
        ec = EJBContainer.createEJBContainer();
        customerEJB = (CustomerEJB)ec.getContext().
                lookup("java:global/classes/CustomerEJB!dat076.frukostklubben.ejb.CustomerEJB");
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (ec != null){
            ec.close();
        }
    }
    
    @Test
    public void testCustomer() throws Exception{
        // Creates an instance of flight

        
        // Persists the flight to the database


        // Retrieves all the flights from the database

        
        //Gets a flight by its it

        
        
        //Updating the flight

        
                
        //If we delete the last flight the list of flights should på empty.
 
    }
}
