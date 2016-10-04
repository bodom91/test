package ru.dnsprice.com.dao;

import ru.dnsprice.com.model.City;

import java.util.List;

/**
 * Created by Максим on 05.10.2016.
 */
public interface CityDAO {

    public void addCity(City city);

    public City getCity(Integer id);

    public void removeCity(Integer id);

    public List<City> getList();

}
