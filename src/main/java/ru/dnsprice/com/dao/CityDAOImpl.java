package ru.dnsprice.com.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dnsprice.com.model.City;

import java.util.List;

/**
 * Created by Максим on 05.10.2016.
 */

@Repository
public class CityDAOImpl implements CityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCity(City city) {
        sessionFactory.getCurrentSession().save(city);
    }

    public City getCity(Integer id) {
       City city = (City) sessionFactory.getCurrentSession().get(City.class, id);
        if (null != city) {
            return city;
        } else return null;
    }

    public void removeCity(Integer id) {
        City city = (City) sessionFactory.getCurrentSession().load(City.class , id);
        if (null != city) {
            sessionFactory.getCurrentSession().delete(city);
        }
    }

    public List<City> getList() {
        return sessionFactory.getCurrentSession().createQuery("from City")
                .list();
    }
}
