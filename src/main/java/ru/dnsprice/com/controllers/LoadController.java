package ru.dnsprice.com.controllers;

import com.sun.tracing.dtrace.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.dnsprice.com.model.City;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.model.UserCity;
import ru.dnsprice.com.service.CityService;
import ru.dnsprice.com.service.UserCityService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shestakov.m on 12.10.2016.
 */
@Controller
@SessionAttributes("user")
public class LoadController {

    @ModelAttribute
    public User createUser(){
        return new User();
    }

    @Resource
    private UserCityService userCityService;

    @Resource
    private CityService cityService;

    @RequestMapping(value = "/load" , method = RequestMethod.GET)
    public ModelAndView getLoad(@ModelAttribute ("user") User user, Model model , @ModelAttribute ("citych") City citych, String select) {
        if (user.getUserid() == 0) {
            return new ModelAndView("/error/403");
        } else {
            List<UserCity> userCities = userCityService.getList(user.getName());
            List<City> city = cityService.getList();
            List<City> resultCity = new ArrayList<City>();
            for (UserCity x : userCities) {
                resultCity.add(cityService.getCity(x.getCity()));
            }
            if (citych == null) {
                City city1 = city.get(0);
                model.addAttribute("citych", city1);
            } else {
                City city1 = citych;
                model.addAttribute("citych", city1);
            }
            if (select == null) {
                select = "3";
            }
            model.addAttribute("pages", select);
            model.addAttribute("city2" , resultCity);
            return new ModelAndView("load", "user", user);
        }
    }



}
