package dat076.frukostklubben.persistenceEJB;

import dat076.frukostklubben.model.User;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author freein Edited by: Anders, 2012-10-17
 */
@Stateless
@LocalBean
public class UserEJB extends AbstractPersistenceEJB<User,String> {

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    public User find(String mail){
        return super.find(User.class, mail);
    }

    public List<User> findAll() {
        TypedQuery<User> query = em.createNamedQuery("findAllUsers", User.class);
        return query.getResultList();
    }
}
