/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author freein
 */
@Embeddable
public class Address implements Serializable {
    private String street;
    private Integer zip;
    private String city;

    // ======================================
    // =          Getters & Setters         =
    // ======================================    
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
}
