/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;
import dat076.frukostklubben.ejb.FlightEJB;
import dat076.frukostklubben.ejb.ShoppingCartEJB;
import dat076.frukostklubben.model.Flight;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Johan
 */
@Named("cartbb")
@SessionScoped
public class CartBB implements Serializable  {
    
    @EJB
    private ShoppingCartEJB cartEJB;
    @EJB
    private FlightEJB flightEJB;
    
    public void addFlight(Long id){
        cartEJB.addFlight(flightEJB.findFlightById(id));
    }
    
    public String action(){
        return "cart";
    }
    
    public List<Flight> getFlights(){
        return cartEJB.getCartItems();
    }
    
}
