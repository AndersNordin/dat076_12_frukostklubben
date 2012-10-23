/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.ejb;

import dat076.frukostklubben.model.Flight;
import dat076.frukostklubben.model.FinnishedOrder;
import dat076.frukostklubben.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fredrik
 */
@Stateful
@StatefulTimeout(value = 30) //Containern tar bort bönan efter 30 overksamma min
@LocalBean
@Named("cart")
public class ShoppingCartEJB {
    @EJB
    MailEJB orderEJB;
    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;
    
    private List<Flight> cartItems = new ArrayList<>();
    
    public ShoppingCartEJB(){} //Needed for container  
    

    public void addFlight(Flight flight) {
        if(find(flight.getId()) == null){
      //  if (!cartItems.contains(flight)){ //Får det inte att fungera om man går direkt hit, utan bara via CartBB
            cartItems.add(flight);          //Måste lägga till/ta bort med id, går inte direkt med flight?
        }
    }
    

    public void removeItem(Flight flight) {
        Flight f = find(flight.getId());
        if(f != null){
      //  if (cartItems.contains(flight)){
            cartItems.remove(f);
        }
    }
    
    private Flight find(Long id){
        for(Flight f : cartItems){
            if(f.getId() == id){
                return f;
            }
        }
        return null;
    }
    

    public Integer getNumberOfItems() {
        if (cartItems == null || cartItems.isEmpty()){
            return 0;
        }
        else{
            return cartItems.size();
        }
    }
    

    public Double getTotal() {
        if (cartItems == null || cartItems.isEmpty()){
            return 0D;
        }
        else{
            double total = 0;
            for (Flight flight : cartItems){
                total += flight.getCost();
            }
            return total;
        }      
        
    }
    
    

    public List<Flight> getCartItems() {
        return cartItems;
    }
    
    public void empty() {
        cartItems.clear();
    }
    
    @Remove //Detta betyder att denna böna försvinner när checkout() anropas.
    public void checkout(User user) {
        FinnishedOrder order = new FinnishedOrder();
        order.setFlights(cartItems);
        order.setUser(user); ///Hur vet jag vem???
        user.getFinnishedOrders().add(order);
        em.persist(order);
        cartItems.clear();
    }   
}
