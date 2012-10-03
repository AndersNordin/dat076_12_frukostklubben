/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.webshop;

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
/*Möjligt att man måste använda @Localbean och sedan göra ett interface som
man i denna klass implementerar och i den klassen behöver man skriva @Remote*/
public class FlightEJB implements FlightEJBRemote {
    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    @Override
    public List<Flight> findFlights() {
        TypedQuery<Flight> query = em.createNamedQuery("findAllFlights", Flight.class);
        return query.getResultList();
    }
    @Override
    public Flight findFlightById(Long id) {
        return em.find(Flight.class, id);
    }
    @Override
    public Flight createFlight(Flight flight) {
        em.persist(flight);
        return flight;
    }
    @Override
    public void deleteFlight(Flight flight) {
        em.remove(em.merge(flight));
    }
    @Override
    public Flight updateFlight(Flight flight) {
        return em.merge(flight);
    }
}

