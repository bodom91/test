package ru.dnsprice.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dnsprice.com.dao.UserCityDAO;
import ru.dnsprice.com.model.UserCity;

import java.util.List;

/**
 * Created by shestakov.m on 10.10.2016.
 */
@Service("UserCity")
public class UserCityServiceImpl implements UserCityService {

    @Autowired
    private UserCityDAO userCityDAO;

    @Transactional
    public void addUserCity(UserCity userCity) {
        userCityDAO.addUserCity(userCity);
    }
    @Transactional
    public void removeUserCity(Integer id) {
        userCityDAO.removeUserCity(id);
    }
    @Transactional
    public List<UserCity> getList(String name) {
        return userCityDAO.getList(name);
    }
    @Transactional
    public void delUserCity(UserCity usercity) {
        userCityDAO.delUserCity(usercity);
    }
}
