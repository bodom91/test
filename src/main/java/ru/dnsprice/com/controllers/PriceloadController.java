package ru.dnsprice.com.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dnsprice.com.model.City;
import ru.dnsprice.com.model.Goods;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.model.UserCity;
import ru.dnsprice.com.service.CityService;
import ru.dnsprice.com.service.UserCityService;
import ru.dnsprice.com.utils.GetAvailableCity;
import ru.dnsprice.com.utils.api.Compains;
import ru.dnsprice.com.utils.logic.ServiceClass;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shestakov.m on 05.10.2016.
 */
@Controller
@SessionAttributes({"user","citych"})
public class PriceloadController {

    @ModelAttribute
    public User createUser(){
        return new User();
    }

    @Resource
    private CityService cityService;

    @Resource
    private ServiceClass serviceClass;

    @Resource
    private GetAvailableCity getAvailableCity;

    @Resource
    private Compains compains;

    @Resource
    private UserCityService userCityService;

    @RequestMapping(value = "/priceload", method = RequestMethod.GET)
    public ModelAndView controller(@ModelAttribute ("user") User user, Model model) {
        if (user.getUserid() == 0) {
            return new ModelAndView("/error/403");
        } else {
            // Добавляем список городов на страницу
            List<UserCity> userCities = userCityService.getList(user.getName());
            List<City> city = cityService.getList();
            List<City> resultCity = new ArrayList<City>();
            for (UserCity x : userCities) {
                resultCity.add(cityService.getCity(x.getCity()));
            }
            City city1 = city.get(0);
            model.addAttribute("citych", city1);
            model.addAttribute("city2" , resultCity);
            return new ModelAndView("priceload", "user", user);
        }
    }

    @RequestMapping(value = "/priceload", method = RequestMethod.POST)
    public ModelAndView getCity(@ModelAttribute ("user") User user, Model model,@ModelAttribute ("citych") City citych, String page) {
        if (user.getUserid() == 0) {
            return new ModelAndView("/error/403");
        } else {
            // Добавляем товары на страницу
            if (page == null) {
                page = "1";
            }
            List<Goods> listGoods = new ArrayList<Goods>();
            ObjectMapper objectMapper = new ObjectMapper();
            JSONObject jObjectPage = (JSONObject) compains.getGoods(String.valueOf(citych.getId()),page, "100");
            JSONObject objPager = (JSONObject) jObjectPage.get("pager");
            JSONArray arrayOffers = (JSONArray) jObjectPage.get("offers");
            //Получаем количество страниц
            String pagesCount = String.valueOf(objPager.get("pagesCount"));
            //ПОлучаем список товаров
            serviceClass.parseGoods(arrayOffers,listGoods);
            model.addAttribute("page" , page);
            model.addAttribute("goods" , listGoods);
            model.addAttribute("pages" , pagesCount);
            // Добавляем список городов на страницу
            getAvailableCity.getCity(user, citych, model);
            System.out.println("all right");
            return new ModelAndView("priceload", "user", user);
        }
    }
}
