/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.ejb;

import dat076.frukostklubben.model.Flight;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

/**
 *
 * @author Fredrik
 */
@Stateful
@StatefulTimeout(value = 30) //Containern tar bort bönan efter 30 overksamma min
@LocalBean
public class ShoppingCartEJB implements ShoppingCartEJBRemote {
    
    
    private List<Flight> cartItems = new ArrayList<>();
    
    public ShoppingCartEJB(){} //Needed for container
    
    
    @Override
    public void addFlight(Flight flight) {
        if (!cartItems.contains(flight)){
            cartItems.add(flight);
        }
    }
    
    @Override
    public void removeItem(Flight flight) {
        if (cartItems.contains(flight)){
            cartItems.remove(flight);
        }
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
