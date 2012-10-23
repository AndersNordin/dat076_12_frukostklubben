package dat076.frukostklubben.bb;

import dat076.frukostklubben.model.User;
import dat076.frukostklubben.persistenceEJB.UserEJB;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author anordin
 *
 * Holds the user logged in. For creating a new one SignupBB is used.
 *
 */
@SessionScoped
@Named("userController")
public class UserController implements Serializable {

    @EJB
    UserEJB userEJB;
    User user;
    private String mail;
    private String password;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String signin() {
        user = userEJB.find(mail);
        if (user != null) {
            if (user.getPasswd().equals(password)) {
                return "chat?faces-redirect=true";
            }
            return "index?faces-redirect=true";
        }
        return null;

    }
}
