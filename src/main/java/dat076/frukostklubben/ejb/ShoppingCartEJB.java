/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.ejb;

import dat076.frukostklubben.model.Flight;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.inject.Named;

/**
 *
 * @author Fredrik
 */
@Stateful
@StatefulTimeout(value = 30) //Containern tar bort bönan efter 30 overksamma min
@LocalBean
@Named("cart")
public class ShoppingCartEJB implements ShoppingCartEJBRemote {
    @EJB
    MailEJB orderEJB;
    
    private List<Flight> cartItems = new ArrayList<>();
    
    public ShoppingCartEJB(){} //Needed for container  
    
    @Override
    public void addFlight(Flight flight) {
        if(find(flight.getId()) == null){
      //  if (!cartItems.contains(flight)){
            cartItems.add(flight);
        }
    }
    
    @Override
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
    
    @Override
    public Integer getNumberOfItems() {
        if (cartItems == null || cartItems.isEmpty()){
            return 0;
        }
        else{
            return cartItems.size();
        }
    }
    
    @Override
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
    
    @Override
    public void empty() {
        cartItems.clear();
    }
    
    @Remove //Detta betyder att denna böna försvinner när checkout() anropas.
    @Override
    public void checkout() {
        // Do some business logic
        cartItems.clear();
    }   
}
