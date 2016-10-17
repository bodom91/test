package ru.dnsprice.com.utils;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.dnsprice.com.model.City;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.model.UserCity;
import ru.dnsprice.com.service.CityService;
import ru.dnsprice.com.service.UserCityService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shestakov.m on 17.10.2016.
 */
@Service("avCity")
public class GetAvailableCity {

    @Resource
    private UserCityService userCityService;

    @Resource
    private CityService cityService;

    public void getCity(User user, City city, Model model) {
        List<UserCity> userCities = userCityService.getList(user.getName());
        List<City> city2 = cityService.getList();
        List<City> resultCity = new ArrayList<City>();
        for (UserCity x : userCities) {
            resultCity.add(cityService.getCity(x.getCity()));
        }
        if (city == null) {
            City city1 = city2.get(0);
            model.addAttribute("citych", city1);
        } else {
            City city1 = city;
            model.addAttribute("citych", city1);
        }
        model.addAttribute("city2" , resultCity);
    }
}
