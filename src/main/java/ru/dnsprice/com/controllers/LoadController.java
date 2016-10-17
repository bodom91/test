package ru.dnsprice.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.dnsprice.com.model.City;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.service.CityService;
import ru.dnsprice.com.service.UserCityService;
import ru.dnsprice.com.utils.GetAvailableCity;

import javax.annotation.Resource;

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
    private GetAvailableCity getAvailableCity;

    @Resource
    private CityService cityService;

    @RequestMapping(value = "/load" , method = RequestMethod.GET)
    public ModelAndView getLoad(@ModelAttribute ("user") User user, Model model , @ModelAttribute ("citych") City citych) {
        if (user.getUserid() == 0) {
            return new ModelAndView("/error/403");
        } else {
            getAvailableCity.getCity(user, citych, model);
            return new ModelAndView("load", "user", user);
        }
    }



}
