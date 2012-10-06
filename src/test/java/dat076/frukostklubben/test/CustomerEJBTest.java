/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.test;

import dat076.frukostklubben.ejb.FlightEJB;
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
    
    @Test
    public void testCustomer() throws Exception{
        
    }
}
