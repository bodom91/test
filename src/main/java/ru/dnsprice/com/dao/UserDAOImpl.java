package ru.dnsprice.com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

    public void addContact(User user) {
        String hash = passToHash.encode(user.getPassword());
        user.setPassword(hash);
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

    public boolean checkUser(User user) {
        List<User> users = listContact();
        User userBD = null;
        try {
            for (User listUser : users) {
                if (listUser.getName().equals(user.getName()))
                    userBD = listUser;
            } if (passToHash.matches(user.getPassword(),userBD.getPassword())) {
                return true;
            } else return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

}
