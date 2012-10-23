package dat076.frukostklubben.test;

import dat076.frukostklubben.persistenceEJB.UserEJB;
import dat076.frukostklubben.model.Address;
import dat076.frukostklubben.model.User;
import dat076.frukostklubben.security.SubjectGroup;
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
        User user = new User();
        user.addGroup(SubjectGroup.USER);
        user.setAddress(new Address());
        user.setFirstName("Fredrik");
        user.setLastName("Einarsson");
        user.setMail("Einarsson.F@gmail.com");
        user.setPasswd("fredrik");
        
        // Persists the user to the database
        userEJB.create(user);
        
        //Try aquire it again
        User user2 = userEJB.find(user.getMail());
        Assert.assertEquals(user2.getFirstName(), user.getFirstName());
        
        //Try update
        user.setLastName("Andersson");
        userEJB.update(user);
        User user3 = userEJB.find(user.getMail());
        Assert.assertEquals(user3.getLastName(),"Andersson");
        
        //Try delete
        userEJB.delete(user);
        user = userEJB.find(user.getMail());
        Assert.assertNull(user);
        
        //findall
        List<User> list = userEJB.findAll();
        Assert.assertNotNull(list);
    }
}
