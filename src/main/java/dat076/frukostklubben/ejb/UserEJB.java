package dat076.frukostklubben.ejb;

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
public class UserEJB implements UserEJBRemote {

    @PersistenceContext(unitName = "projectPU")
    private EntityManager em;

    @Override
    public List<User> findUsers() {
        TypedQuery<User> query = em.createNamedQuery("findAllUsers", User.class);
        return query.getResultList();
    }

    @Override
    public User findUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User createUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        em.remove(em.merge(user));
    }

    @Override
    public User updateUser(User user) {
        return em.merge(user);
    }

    @Override
    public User findByMail(String mail) {
        String query = "select u from UserObj u where u.mail = :mail";
        TypedQuery<User> q = em.createQuery(query, User.class);
        q.setParameter("mail", mail);
        return q.getSingleResult();
    }
}
