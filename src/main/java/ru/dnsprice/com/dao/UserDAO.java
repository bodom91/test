package ru.dnsprice.com.dao;

import ru.dnsprice.com.model.User;

import java.util.List;

/**
 * Created by Максим on 04.10.2016.
 */
public interface UserDAO {

    public void addContact(User user);

    public List<User> listContact();

    public void removeContact(Integer id);
    
}
