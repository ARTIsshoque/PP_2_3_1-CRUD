package app.service;

import app.dao.EntityDao;
import app.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements EntityService<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final EntityDao<User> dao;

    public UserService(EntityDao<User> dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public void add(User user) {
        LOGGER.info("Adding user: {}", user);
        dao.add(user);
    }

    @Transactional
    @Override
    public User findById(Long id) {
        return dao.findById(id);
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Transactional
    @Override
    public void update(User user) {
        LOGGER.info("Updating user: {}", user);
        dao.update(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting user with id {}", id);
        dao.delete(id);
    }
}
