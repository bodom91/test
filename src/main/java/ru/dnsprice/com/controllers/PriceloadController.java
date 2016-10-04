package ru.dnsprice.com.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dnsprice.com.model.City;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.service.CityService;
import ru.dnsprice.com.utils.api.Compains;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shestakov.m on 05.10.2016.
 */
@Controller
@SessionAttributes("user")
public class PriceloadController {

    @ModelAttribute
    public User createUser(){
        return new User();
    }

    @Resource
    private CityService cityService;

    @RequestMapping(value = "/priceload", method = RequestMethod.GET)
    public ModelAndView controller(@ModelAttribute ("user") User user, Model model) {
        if (user.getName() == null) {
            return new ModelAndView("/error/403");
        } else {
            List<City> gh = cityService.getList();
            model.addAttribute("citych", new City());
            model.addAttribute("city2" , gh);
            return new ModelAndView("priceload", "user", user);
        }
    }

    @RequestMapping(value = "/priceload", method = RequestMethod.POST)
    public ModelAndView getCity(@ModelAttribute ("user") User user, Model model,@ModelAttribute ("citych") City citych) {
        if (user.getName() == null) {
            return new ModelAndView("/error/403");
        } else {
            List<City> gh = cityService.getList();
            System.out.println(citych.getId());
            model.addAttribute("city2" , gh);
            System.out.println("all right");
            return new ModelAndView("priceload", "user", user);
        }
    }

}
