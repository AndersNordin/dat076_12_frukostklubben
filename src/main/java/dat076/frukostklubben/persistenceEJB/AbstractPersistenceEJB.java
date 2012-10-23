/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.persistenceEJB;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Fredrik
 */

public abstract class AbstractPersistenceEJB<T,K>{
    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;
    
    public void create(T t) {
        em.persist(t);
    }


    public void delete(T t) {
        em.remove(em.merge(t));
    }


    public T update(T t) {
        return em.merge(t);
    }
    
    public  T find(Class clazz,K k) {
       return (T) em.find(clazz, k);
    }
}
