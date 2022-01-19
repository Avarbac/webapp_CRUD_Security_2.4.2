package org.example.dao;

import org.example.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public User getUserById(Long id) {
        return manager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return manager.createQuery("select distinct u from User u join fetch u.roles",
                        User.class)
                .getResultList();
    }

    @Override
    public void saveUser(User user) {
        manager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {

        manager.remove(getUserById(id));
    }

    @Override
    public void updateUser(User user) {
        User updatedUser = user;
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setAge(user.getAge());
        user.setRoles(updatedUser.getRoles());
        manager.merge(updatedUser);
    }

    @Override
    public User getUserByName(String name) {
        return manager.createQuery("select u from User u where u.name =?1", User.class)
                .setParameter(1, name)
                .getSingleResult();
    }
}