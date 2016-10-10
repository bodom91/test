package ru.dnsprice.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
 * Created by shestakov.m on 10.10.2016.
 */
@Controller
@SessionAttributes("user")
public class CityController {

    @ModelAttribute
    public User createUser(){
        return new User();
    }

    @Resource
    private CityService cityService;

    @Resource
    private UserCityService userCityService;

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public ModelAndView getCity(@ModelAttribute ("user") User user, Model model) {
        if (user.getName() == null) {
            return new ModelAndView("/error/403");
        } else {
            List<UserCity> userCities = userCityService.getList();
            List<City> city = cityService.getList();
            String check = "";
            model.addAttribute("userCity", userCities);
            model.addAttribute("check" , check);
            model.addAttribute("city", city);
        }
        return new ModelAndView("city" , "user", user);
    }

    @RequestMapping(value = "city", method = RequestMethod.POST)
    public ModelAndView addCity(@ModelAttribute ("user") User user, String check) {
        if (user.getName() == null) {
            return new ModelAndView("/error/403");
        } else {
            String[] id = check.split(",");
            for (String x : id) {
                UserCity userCity = new UserCity();
                userCity.setName(user.getName());
                userCity.setCity(Integer.valueOf(x));
                userCityService.addUserCity(userCity);
            }
        }
        return new ModelAndView("city", "user", user);
    }
}
