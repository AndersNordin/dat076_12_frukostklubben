/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.ShoppingCartEJB;
import dat076.frukostklubben.model.Flight;
import dat076.frukostklubben.model.User;
import dat076.frukostklubben.persistenceEJB.FlightEJB;
import dat076.frukostklubben.persistenceEJB.UserEJB;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Johan
 */
@Named("cartbb")
@SessionScoped
public class CartBB implements Serializable {

    @EJB
    private ShoppingCartEJB cartEJB;
    @EJB
    private FlightEJB flightEJB;
    @EJB
    private UserEJB userEJB;

    /*    public void addFlight(Long id){
     cartEJB.addFlight(flightEJB.findFlightById(id));
     }
     */
    public String addFlight(Flight flight) {
        cartEJB.addFlight(flight);
        return "cart?faces-redirect=true";
    }

    public List<Flight> getFlights() {
        return cartEJB.getCartItems();
    }

    public String removeFlight(Long id) {
        cartEJB.removeItem(flightEJB.find(id));
        return "cart?faces-redirect=true";
    }

    public String checkout(String mail) {
        if (mail == null || cartEJB.getCartItems().isEmpty()) {
            return "/browse?faces-redirect=true";
        }
        User user = userEJB.find(mail);
        cartEJB.checkout(user);
        return "/index?faces-redirect=true"; //när en order är skickad. var ska jag då?
    }
}
