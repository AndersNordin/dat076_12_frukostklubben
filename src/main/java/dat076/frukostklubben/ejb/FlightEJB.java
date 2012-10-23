/*
 * If something is added here, add extra test case in FlightEJBTest
 */
package dat076.frukostklubben.ejb;

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
public class FlightEJB {

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;


    public List<Flight> findFlights() {
        TypedQuery<Flight> query = em.createNamedQuery("findAllFlights", Flight.class);
        return query.getResultList();
    }


    public Flight findFlightById(Long id) {
        return em.find(Flight.class, id);
    }


    public Flight createFlight(Flight flight) {
        em.persist(flight);
        return flight;
    }


    public void deleteFlight(Flight flight) {
        em.remove(em.merge(flight));
    }


    public Flight updateFlight(Flight flight) {
        return em.merge(flight);
    }
    /*
     * Metoden söker i databasen efter antingen från och till eller någon
     * av dem.
     */

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
