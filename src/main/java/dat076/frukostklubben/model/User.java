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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Fredrik
 * Edited by: Anders, 2012-10-17
 */
@Entity
@NamedQuery(name = "findAllUsers", query = "SELECT u FROM User u")
public class User implements Serializable {
    
    // Access level for user account. 
    public enum Access{
        ADMIN,
        USER
    }
    
    
     @Id @GeneratedValue
     private long id;
     
     private String firstName;
     private String lastName;
     private String mail;
     @Embedded
     @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
     private Address address;
     private String password;
     private Access access;



    public User(String firstName, String lastName, String mail, Address address, Access access) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.address = address;
        this.access = access;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }   

    public void setId(long id) {
        this.id = id;
    }

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public void setAccess(Access access) {
        this.access = access;
    }

    public Access getAccess() {
        return access;
    }
}
