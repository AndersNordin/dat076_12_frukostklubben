/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.test;

import dat076.frukostklubben.ejb.ShoppingCartEJB;
import dat076.frukostklubben.model.Address;
import dat076.frukostklubben.model.FinnishedOrder;
import dat076.frukostklubben.model.Flight;
import dat076.frukostklubben.model.User;
import dat076.frukostklubben.persistenceEJB.OrderEJB;
import dat076.frukostklubben.security.SubjectGroup;
import java.util.ArrayList;
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
public class OrderEJBTest {
    
    // ======================================
    // =             Fields             =
    // ======================================
    private static EJBContainer ec;
    private static OrderEJB ejb;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initContainer() throws Exception {
        //Create an Ejb container and put in an ShoppingCartEJB instance
        ec = EJBContainer.createEJBContainer();
        ejb = (OrderEJB)ec.getContext().
                lookup("java:global/classes/OrderEJB");
        //"java:global/classes/OrderEJB!dat076.frukostklubben.ejb.OrderEJB"
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (ec != null){
            ec.close();
        }
    }
    
    @Test
    public void test(){
        // Creates an instance of flight
        Flight flight = new Flight("Resa 1","Landvetter", "Frankfurt",new Date(Calendar.getInstance().getTimeInMillis()));
        flight.setCost(2.3);
        
        // Creates an instance of User
        User user = new User();
        user.addGroup(SubjectGroup.USER);
        user.setAddress(new Address());
        user.setFirstName("Fredrik");
        user.setLastName("Einarsson");
        user.setMail("Einarsson.F@gmail.com");
        user.setPasswd("fredrik");
        
        //Crete order
        List<Flight> flights = new ArrayList<>();
        FinnishedOrder order = new FinnishedOrder();
        order.setFlights(flights);
        order.setUser(user);
        
        //Persist and find
        ejb.create(order);
        FinnishedOrder order2 = ejb.find(order.getId());
        Assert.assertEquals(order.getId(), order2.getId());
        
    }
}
