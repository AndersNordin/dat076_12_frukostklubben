/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.FlightEJB;
import dat076.frukostklubben.model.Flight;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Johan
 */
@Named("search")
@SessionScoped
public class SearchFlightBean implements Serializable {
    
    private String from;
    private String to;
    private String date;
    private List<Flight> flights;
    @EJB
    FlightEJB flight;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
       /* try{
            DateFormat df = new SimpleDateFormat("");
            this.date = df.parse(date);
        }*/
    }
    
    public List<Flight> getFlights() {
        return flights;
    }
    
    public String action(){
        return "search";
    }
    
    public void actionListener(){
        flights =  flight.searchFlights(from, to, date);
    }   
}