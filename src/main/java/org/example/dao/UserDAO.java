package org.example.dao;

import org.example.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager manager;

    public User getUserById(int id) {
        return manager.find(User.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return manager.createQuery("FROM User", User.class)
                .getResultList();
    }

    public void saveUser(User user) {
        manager.persist(user);
    }

    public void deleteUser(int id) {
        manager.remove(getUserById(id));
    }

    public void updateUser(User user, int id) {
        User updatedUser = getUserById(id);
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setAge(user.getAge());
        manager.merge(updatedUser);
    }
}