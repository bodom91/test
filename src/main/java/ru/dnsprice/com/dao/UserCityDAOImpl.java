package ru.dnsprice.com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dnsprice.com.model.UserCity;

import java.util.List;

/**
 * Created by shestakov.m on 10.10.2016.
 */
@Repository
public class UserCityDAOImpl implements UserCityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUserCity(UserCity userCity) {
        sessionFactory.getCurrentSession().save(userCity);
    }

    public void removeUserCity(Integer id) {
        UserCity userCity = (UserCity) sessionFactory.getCurrentSession().load(
                UserCity.class, id);
        if (null != userCity) {
            sessionFactory.getCurrentSession().delete(userCity);
        }
    }



    public List<UserCity> getList(String name) {
        return sessionFactory.getCurrentSession().createQuery("FROM UserCity WHERE name=:name").setParameter("name", name)
                .list();
    }

    public void delUserCity(UserCity usercity) {
        UserCity user = (UserCity) sessionFactory.getCurrentSession().createQuery("FROM UserCity WHERE name=:name AND city=:city")
                .setParameter("name", usercity.getName()).setParameter("city", usercity.getCity()).uniqueResult();
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
