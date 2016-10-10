package ru.dnsprice.com.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dnsprice.com.model.City;
import ru.dnsprice.com.model.Goods;
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
@SessionAttributes({"user","citych"})
public class PriceloadController {

    @ModelAttribute
    public User createUser(){
        return new User();
    }

    @Resource
    private CityService cityService;

    @Resource
    private Compains compains;

    @RequestMapping(value = "/priceload", method = RequestMethod.GET)
    public ModelAndView controller(@ModelAttribute ("user") User user, Model model) {
        if (user.getName() == null) {
            return new ModelAndView("/error/403");
        } else {
            // Добавляем список городов на страницу
            List<City> city = cityService.getList();
            City city1 = city.get(0);
            model.addAttribute("citych", city1);
            model.addAttribute("city2" , city);
            return new ModelAndView("priceload", "user", user);
        }
    }

    @RequestMapping(value = "/priceload", method = RequestMethod.POST)
    public ModelAndView getCity(@ModelAttribute ("user") User user, Model model,@ModelAttribute ("citych") City citych, String page) {
        if (user.getName() == null) {
            return new ModelAndView("/error/403");
        } else {
            // Добавляем товары на страницу
            if (page == null) {
                page = "1";
            }
            List<Goods> listGoods = new ArrayList<Goods>();
            ObjectMapper objectMapper = new ObjectMapper();
            JSONObject jObjectPage = (JSONObject) compains.getGoods(String.valueOf(citych.getId()),page);
            JSONObject objPager = (JSONObject) jObjectPage.get("pager");
            JSONArray arrayOffers = (JSONArray) jObjectPage.get("offers");
            //Получаем количество страниц
            String pagesCount = String.valueOf(objPager.get("pagesCount"));
            //ПОлучаем список товаров
            for (Object goods : arrayOffers) {
                Goods good = null;
                try {
                    good = objectMapper.readValue(goods.toString(), Goods.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                listGoods.add(good);
            }
            model.addAttribute("page" , page);
            model.addAttribute("goods" , listGoods);
            model.addAttribute("pages" , pagesCount);
            // Добавляем список городов на страницу
            List<City> city = cityService.getList();
            model.addAttribute("city2" , city);
            model.addAttribute("citych", citych);
            System.out.println("all right");
            return new ModelAndView("priceload", "user", user);
        }
    }
}
