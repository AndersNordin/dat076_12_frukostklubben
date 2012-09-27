/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.webshop;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author anders
 */
@Entity
public class Flight {
    @Id @GeneratedValue
    private long id;
    private String name;
    private String fromAirport;
    private String toAirport;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    public Flight() {
    }

    public Flight(long id, String name, String fromAirport, String toAirport, Date date) {
        this.id = id;
        this.name = name;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromAirport() {
        return fromAirport;
    }

    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
