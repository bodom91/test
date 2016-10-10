package ru.dnsprice.com.check;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.JsonArray;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.impl.SessionFactoryImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.dnsprice.com.dao.CityDAOImpl;
import ru.dnsprice.com.model.City;
import ru.dnsprice.com.model.Goods;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.service.CityServiceImpl;
import ru.dnsprice.com.service.UserServiceImpl;
import ru.dnsprice.com.utils.api.Compains;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shestakov.m on 04.10.2016.
 */
public class check extends BCryptPasswordEncoder {


    public static void main(String[] args) throws IOException {
//        Compains compains = new Compains();
//        ObjectMapper objectMapper = new ObjectMapper();
//        JSONObject jObjectPage = (JSONObject) compains.getGoods("21342905","1");
//        JSONObject objPager = (JSONObject) jObjectPage.get("pager");
//        JSONArray arrayOffers = (JSONArray) jObjectPage.get("offers");
//        //Получаем количество страниц
//        String pagesCount = String.valueOf(objPager.get("pagesCount"));
//        System.out.println(pagesCount);
        //System.out.println(compains.getGoods("21342905","1"));
    }
}
