package dat076.frukostklubben.test;

import dat076.frukostklubben.ejb.UserEJB;
import dat076.frukostklubben.model.Address;
import dat076.frukostklubben.model.User;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author freein
 * Edit by: Anders, 2012-10-17
 */
public class UserEJBTest {
        // ======================================
    // =             Fields             =
    // ======================================
    private static EJBContainer ec;
    private static UserEJB userEJB;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initContainer() throws Exception {
        //Create an Ejb container and put in an UserEJB instance
        ec = EJBContainer.createEJBContainer();
        userEJB = (UserEJB)ec.getContext().
                lookup("java:global/classes/UserEJB!dat076.frukostklubben.ejb.UserEJB");
    }

    @AfterClass
    public static void closeContainer() throws Exception {
        if (ec != null){
            ec.close();
        }
    }
    
    @Test
    public void testUser() throws Exception{
        // Creates an instance of User
        User user = new User("Anders", "Nordin", "mail@mail.com", new Address());
        
        // Persists the user to the database
        user  = userEJB.createUser(user);
        Assert.assertNotNull(user);
        
        // Retrieves all the users from the database
        List<User> users = userEJB.findUsers();
        Assert.assertNotNull(users);
        
        //Gets a user by its id
        /*User user2 = userEJB.findUserById(user.getId());
        Assert.assertEquals(user2.getId(), user.getId());*/
        
        //Updating the user
      /*  user.setMail("Anders@mail.com");
        userEJB.updateUser(user);
        User user3 = userEJB.findUserById(user.getId());
        Assert.assertEquals(user3.getMail(),"Anders@mail.com");*/
        
                
        //If we delete the last user the list of users should på empty.
        userEJB.deleteUser(user);
        users = userEJB.findUsers();
        Assert.assertTrue(users.isEmpty());   
 
    }
}
