/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.ejb;

import dat076.frukostklubben.model.Customer;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author freein
 */
@Stateless
@LocalBean
public class CustomerEJB implements CustomerEJBRemote {

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;
    
    @Override
    public List<Customer> findCustomers() {
        TypedQuery<Customer> query = em.createNamedQuery("findAllCustomers", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

    @Override
    public void deleteCustomer(Customer customer) {
        em.remove(em.merge(customer));
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return em.merge(customer);
    }
}
