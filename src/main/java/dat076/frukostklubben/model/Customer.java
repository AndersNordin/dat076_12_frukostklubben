/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Fredrik
 */
@Entity
public class Customer implements Serializable {
     @Id @GeneratedValue
     private long id;
     
     
}
