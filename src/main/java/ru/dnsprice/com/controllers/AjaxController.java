package ru.dnsprice.com.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dnsprice.com.model.City;
import ru.dnsprice.com.service.CityService;
import ru.dnsprice.com.utils.api.Compains;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by Максим on 07.10.2016.
 */
@Controller
public class AjaxController {

    @Resource
    private CityService cityService;

    @Resource
    private Compains compains;

    @RequestMapping(value = "/ajaxRefreshCity", method = RequestMethod.GET)
    public @ResponseBody String getCompain() {
        List<City> cityFromBD = cityService.getList();
        List<City> city = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            city = objectMapper.readValue(compains.getCompains(), TypeFactory.defaultInstance().constructCollectionType(List.class,City.class));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Bad parsing responce from YM (City)");
        }
        for (City y : cityFromBD) {
            cityService.removeCity(y.getId());
        }
        for (City x : city) {
            cityService.addCity(x);
        }
        return "<br>Next Random # is <b>";
    }
}
