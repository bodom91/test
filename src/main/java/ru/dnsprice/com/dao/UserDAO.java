package ru.dnsprice.com.dao;

import ru.dnsprice.com.model.User;

import java.util.List;

/**
 * Created by Максим on 04.10.2016.
 */
public interface UserDAO {

    public void addContact(User user);

    public User getUserByName(String name);

    public void removeContact(Integer id);

    public List<User> listContact();

    public boolean checkUser(User user);

}
