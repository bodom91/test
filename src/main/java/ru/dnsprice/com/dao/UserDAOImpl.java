package ru.dnsprice.com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dnsprice.com.model.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Максим on 04.10.2016.
 */

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addContact(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public List<User> listContact() {
        return sessionFactory.getCurrentSession().createQuery("from User")
                .list();
    }

    public void removeContact(Integer id) {
        User user = (User) sessionFactory.getCurrentSession().load(
                User.class, id);
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }

    }
}
