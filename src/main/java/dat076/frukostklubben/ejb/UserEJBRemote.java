package dat076.frukostklubben.ejb;

import dat076.frukostklubben.model.User;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author freein
 * Edited by: Anders, 2012-10-17
 */
@Remote
public interface UserEJBRemote {
    List<User> findUsers();
    User findUserById(Long id);
    User createUser(User customer);
    void deleteUser(User customer);
    User updateUser(User customer);
    User findByMail(String mail);
}
