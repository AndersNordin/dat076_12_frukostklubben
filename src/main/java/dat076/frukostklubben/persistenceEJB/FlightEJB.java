/*
 * If something is added here, add extra test case in FlightEJBTest
 */
package dat076.frukostklubben.persistenceEJB;

import dat076.frukostklubben.model.Flight;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author freein Default is container-managed transactions so explicit
 * transactions is not needed.
 */
@Stateless
@LocalBean
public class FlightEJB extends AbstractPersistenceEJB<Flight,Long> {

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    public  Flight find(Long id) {
       return find(Flight.class,id);
    }

    public List<Flight> findAll() {
        TypedQuery<Flight> query = em.createNamedQuery("findAllFlights", Flight.class);
        return query.getResultList();
    }
    

    public List<Flight> searchFlights(Flight flight) {
        if (!flight.getFromAirport().equals("") && !flight.getToAirport().equals("")
                && flight.getDepartureTime() != null) {
            String query = "select f from Flight f where f.fromAirport = :from "
                    + "and f.toAirport = :to and f.departureTime = :date";
            TypedQuery<Flight> q = em.createQuery(query, Flight.class);
            q.setParameter("from", flight.getFromAirport());
            q.setParameter("to", flight.getToAirport());
            q.setParameter("date", flight.getDepartureTime());
            return q.getResultList();
        }
        if (!flight.getFromAirport().equals("") && !flight.getToAirport().equals("")) {
            String query = "select f from Flight f where f.fromAirport = :from "
                    + "and f.toAirport = :to";
            TypedQuery<Flight> q = em.createQuery(query, Flight.class);
            q.setParameter("from", flight.getFromAirport());
            q.setParameter("to", flight.getToAirport());
            return q.getResultList();
        }
        if (!flight.getFromAirport().equals("")) {
            String query = "select f from Flight f where f.fromAirport = :from";
            TypedQuery<Flight> q = em.createQuery(query, Flight.class);
            q.setParameter("from", flight.getFromAirport());
            return q.getResultList();
        }
        if (!flight.getToAirport().equals("")) {
            String query = "select f from Flight f where f.toAirport = :to";
            TypedQuery<Flight> q = em.createQuery(query, Flight.class);
            q.setParameter("to", flight.getToAirport());
            return q.getResultList();
        }
        if (flight.getDepartureTime() != null) {
            String query = "select f from Flight f where f.departureTime = :date";
            TypedQuery<Flight> q = em.createQuery(query, Flight.class);
            q.setParameter("date", flight.getDepartureTime());
            return q.getResultList();
        }
        return null;
    }
}
