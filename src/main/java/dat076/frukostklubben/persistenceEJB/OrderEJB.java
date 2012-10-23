/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.persistenceEJB;

import dat076.frukostklubben.model.FinnishedOrder;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Fredrik
 */
@Stateless
@LocalBean
public class OrderEJB extends AbstractPersistenceEJB<FinnishedOrder,Long> {
    public FinnishedOrder find(Long id){
        return find(FinnishedOrder.class, id);
    }
}
