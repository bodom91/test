package ru.dnsprice.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.dnsprice.com.dao.UserDAO;
import ru.dnsprice.com.model.User;

import java.util.List;

/**
 * Created by Максим on 04.10.2016.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void addContact(User user) {
        userDAO.addContact(user);
    }

    @Transactional
    public List<User> listContact() {
        return userDAO.listContact();
    }

    @Transactional
    public void removeContact(Integer id) {
        userDAO.removeContact(id);
    }
}
