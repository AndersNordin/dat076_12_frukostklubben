/*
 * If something is added here, add extra test case in FlightEJBTest
 */
package dat076.frukostklubben.ejb;

import dat076.frukostklubben.model.Flight;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author freein
 */
@Stateless
@LocalBean
public class FlightEJB implements FlightEJBRemote {
    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    /*@Resource
    private UserTransaction utx;*/

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
        /*try {
            utx.begin();
        } catch (NotSupportedException ex) {
            Logger.getLogger(FlightEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(FlightEJB.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        em.persist(flight);
        /*try {
            utx.commit();
        } catch (RollbackException ex) {
            Logger.getLogger(FlightEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(FlightEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(FlightEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(FlightEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(FlightEJB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(FlightEJB.class.getName()).log(Level.SEVERE, null, ex);
        }*/
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
    
    public List<Flight> searchFlights(Flight flight){
        String query = "select f from Flight f where f.fromAirport = :from "
                + "and f.toAirport = :to";// and f.date = :date
        TypedQuery<Flight> q = em.createQuery(query, Flight.class);
        q.setParameter("from", flight.getFromAirport());
        q.setParameter("to", flight.getToAirport());
        //q.setParameter("date", flight.getDate()); //Denna kommmer ej funka eftersom ett tidobject innehåller även tid
        return q.getResultList();
    }
}

