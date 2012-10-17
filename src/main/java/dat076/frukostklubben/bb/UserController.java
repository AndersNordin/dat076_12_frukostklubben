package dat076.frukostklubben.bb;

import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author anordin
 */

@ManagedBean
@RequestScoped
public class UserController { 
   private ArrayList<String> users;
   private String selectedUser;
 
   @PostConstruct
   public void create (){
	users = new ArrayList <String> ();
	users.add("John");
	users.add("Charley");
	users.add("Priscila");
	users.add("Kate");
	users.add("Emily");
	users.add("Barack");
	users.add("Mia");
	users.add("Arthur");
   }
   public void delete (){
	users.remove(selectedUser);
   }
   public String getSelectedUser() {
	return selectedUser;
   }
   public void setSelectedUser(String selectedUser) {
	this.selectedUser = selectedUser;
   }
   public ArrayList<String> getUsers() {
	return users;
   }
}
