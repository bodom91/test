package ru.dnsprice.com.service;

import ru.dnsprice.com.model.User;

import java.util.List;

/**
 * Created by Максим on 04.10.2016.
 */
public interface UserService {

    public void addContact(User user);

    public List<User> listContact();

    public void removeContact(Integer id);

    public boolean checkUser(User user);

    public User getUserByName(String name);

}
