/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.ejb;

import dat076.frukostklubben.model.Customer;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author freein
 */
@Remote
public interface CustomerEJBRemote {
    List<Customer> findCustomers();
    Customer findCustomerById(Long id);
    Customer createCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
}
