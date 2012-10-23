/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.test;

import dat076.frukostklubben.ejb.ShoppingCartEJB;
import dat076.frukostklubben.model.Flight;
import java.util.Date;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author freein
 */
@Ignore public class ShoppingCartEJBTest {
    
    // ======================================
    // =             Fields             =
    // ======================================
    private static EJBContainer ec;
    private static ShoppingCartEJB ejb;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initContainer() throws Exception {
        //Create an Ejb container and put in an ShoppingCartEJB instance
        ec = EJBContainer.createEJBContainer();
        ejb = (ShoppingCartEJB)ec.getContext().
                lookup("java:global/classes/ShoppingCartEJB!dat076.frukostklubben.ejb.ShoppingCartEJB");
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (ec != null){
            ec.close();
        }
    }
    
    @Test
    public void test(){
        //Add flight to cart
        Flight flight = new Flight("flight 1", "Landvetter", "Glasgow", new Date());
        flight.setCost(88.3);
        ejb.addFlight(flight);
        Assert.assertTrue(ejb.getNumberOfItems() == 1);
        Assert.assertTrue(ejb.getTotal() == 88.3);
        
        //Remove and it should be empty
        ejb.removeItem(flight);
        Assert.assertTrue(ejb.getTotal() == 0);
        
        //Handla och checka ut
        Flight flight1 = new Flight("flight 1", "Landvetter", "Glasgow", new Date());
        flight1.setCost(88.3);
        ejb.addFlight(flight1);
        Flight flight2 = new Flight("flight 1", "Landvetter", "Glasgow", new Date());
        flight2.setCost(8.3);
        ejb.addFlight(flight2);
        
        //ejb.checkout();
        
    }
}
