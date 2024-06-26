package app.dao;

import app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao implements EntityDao<User> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> fromUser = em.createQuery("FROM User", User.class);
        return fromUser.getResultList();
    }

    @Override
    public void update(User user) {
        User storedUser = em.find(User.class, user.getId());
        if (storedUser != null) {
            storedUser.setFirstName(user.getFirstName());
            storedUser.setLastName(user.getLastName());
            storedUser.setEmail(user.getEmail());
            storedUser.setPhoneNumber(user.getPhoneNumber());
            em.merge(storedUser);
        }
    }

    @Override
    public void delete(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }
}
