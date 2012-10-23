/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import dat076.frukostklubben.security.Subject;
import dat076.frukostklubben.security.SubjectGroup;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.OneToMany;

/**
 *
 * @author Fredrik
 * Edited by: Anders, 2012-10-17
 */
@Entity(name="UserObj")
@NamedQuery(name = "findAllUsers", query = "SELECT u FROM UserObj u")
public class User extends Subject implements Serializable {
     @Column(nullable=false)
     private String firstName;
     @Column(nullable=false)
     private String lastName;
     @Embedded
     @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     private Address address = new Address();
     @OneToMany
     private List<Order> orderhistory = new ArrayList<>();

    public User(String firstName, String lastName, String mail, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        setMail(mail);
        this.address = address;
        addGroup(SubjectGroup.USER);
    }

    public User() {
        addGroup(SubjectGroup.USER);
    }    
    
    // ======================================
    // =          Getters & Setters         =
    // ======================================
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrderhistory() {
        return orderhistory;
    }

    public void setOrderhistory(List<Order> orderhistory) {
        this.orderhistory = orderhistory;
    }
    
}