package ru.dnsprice.com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.utils.ConvertPassToHash;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Максим on 04.10.2016.
 */

@Repository
public class UserDAOImpl implements UserDAO {

    @Resource(name = "convertPass")
    public ConvertPassToHash passToHash;

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void addContact(User user) {
        String hash = passToHash.encode(user.getPassword());
        user.setPassword(hash);
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional
    public User getUserByName(String name) {
        User user = (User) sessionFactory.getCurrentSession().createQuery("from User where name=:name")
                .setParameter("name",name).uniqueResult();
        if (user == null) {
            return null;
        }
        return user;
    }

    @Transactional
    public List<User> listContact() {
        return sessionFactory.getCurrentSession().createQuery("from User")
                .list();
    }

    @Transactional
    public void removeContact(Integer id) {
        User user = (User) sessionFactory.getCurrentSession().load(
                User.class, id);
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Transactional
    public boolean checkUser(User user) {
        User userBD = getUserByName(user.getName());
        try {

            if (passToHash.matches(user.getPassword(),userBD.getPassword())) {
                return true;
            } else return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

}
