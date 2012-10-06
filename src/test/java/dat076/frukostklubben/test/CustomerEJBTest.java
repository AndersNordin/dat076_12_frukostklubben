/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.test;

import dat076.frukostklubben.ejb.CustomerEJB;
import dat076.frukostklubben.model.Address;
import dat076.frukostklubben.model.Customer;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import junit.framework.Assert;
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
        // Creates an instance of Customer
        Customer customer = new Customer("Anders", "Nordin", "mail@mail.com", new Address());
        
        // Persists the customer to the database
        customer  = customerEJB.createCustomer(customer);
        Assert.assertNotNull(customer);
        
        // Retrieves all the customers from the database
        List<Customer> customers = customerEJB.findCustomers();
        Assert.assertNotNull(customers);
        
        //Gets a customer by its id
        Customer customer2 = customerEJB.findCustomerById(customer.getId());
        Assert.assertEquals(customer2.getId(), customer.getId());
        
        //Updating the customer
        customer.setMail("Anders@mail.com");
        customerEJB.updateCustomer(customer);
        Customer customer3 = customerEJB.findCustomerById(customer.getId());
        Assert.assertEquals(customer3.getMail(),"Anders@mail.com");
        
                
        //If we delete the last customer the list of customers should på empty.
        customerEJB.deleteCustomer(customer);
        customers = customerEJB.findCustomers();
        Assert.assertTrue(customers.isEmpty());   
 
    }
}
