/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.ejb;

import dat076.frukostklubben.model.Flight;
import javax.ejb.Remote;

/**
 *
 * @author Fredrik
 * This interface should have all methods we want client to call
 * Kan be removed if it is only accessed from server
 */
@Remote
public interface ShoppingCartEJBRemote {
    public void addFlight(Flight flight);
    public void removeItem(Flight flight);
    public Integer getNumberOfItems();
    public Double getTotal();
    public void empty();
    public void checkout();
}
