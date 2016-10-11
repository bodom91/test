package ru.dnsprice.com.dao;

import ru.dnsprice.com.model.UserCity;

import java.util.List;

/**
 * Created by shestakov.m on 10.10.2016.
 */
public interface UserCityDAO {
    public void addUserCity(UserCity userCity);
    public void removeUserCity(Integer id);
    public List<UserCity> getList(String name);
    public void delUserCity(UserCity usercity);
}
