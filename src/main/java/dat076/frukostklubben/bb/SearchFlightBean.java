/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dat076.frukostklubben.bb;

import dat076.frukostklubben.ejb.FlightEJB;
import dat076.frukostklubben.model.Flight;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 *
 * @author Johan
 */
@Named("search")
public class SearchFlightBean {
    
    private String from;
    private String to;
    private Date date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
       /* try{
            DateFormat df = new SimpleDateFormat("");
            this.date = df.parse(date);
        }*/
    }
    
    public String action(){
        return "fdsg"; //Måste ändras
    }
    
    public List<Flight> actionListener(){
        return flight.findFlights(); //Måste ändras
    }
    
}
