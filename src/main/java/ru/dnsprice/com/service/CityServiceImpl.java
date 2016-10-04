package ru.dnsprice.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dnsprice.com.dao.CityDAO;
import ru.dnsprice.com.model.City;

import java.util.List;

/**
 * Created by Максим on 05.10.2016.
 */

@Service("cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDAO cityDAO;

    @Transactional
    public void addCity(City city) {
        cityDAO.addCity(city);
    }

    @Transactional
    public City getCity(Integer id) {
        return cityDAO.getCity(id);
    }

    @Transactional
    public void removeCity(Integer id) {
        cityDAO.removeCity(id);
    }

    @Transactional
    public List<City> getList() {
        return cityDAO.getList();
    }
}
