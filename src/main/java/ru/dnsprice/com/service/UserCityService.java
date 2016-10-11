package ru.dnsprice.com.service;

import ru.dnsprice.com.model.UserCity;

import java.util.List;

/**
 * Created by shestakov.m on 10.10.2016.
 */
public interface UserCityService {
    public void addUserCity(UserCity userCity);
    public void removeUserCity(Integer id);
    public List<UserCity> getList(String name);
    public void delUserCity(UserCity usercity);
}
