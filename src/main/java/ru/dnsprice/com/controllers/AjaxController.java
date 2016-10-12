package ru.dnsprice.com.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.dnsprice.com.model.City;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.model.UserCity;
import ru.dnsprice.com.service.CityService;
import ru.dnsprice.com.service.UserCityService;
import ru.dnsprice.com.utils.api.Compains;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Максим on 07.10.2016.
 */
@Controller
@SessionAttributes("user")
public class AjaxController {

    @ModelAttribute
    public User createUser(){
        return new User();
    }

    @Resource
    private UserCityService userCityService;

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
        return "<br>Refresh is Done<b>";
    }

    @RequestMapping(value = "/pushprice", method = RequestMethod.POST)
    public @ResponseBody String pushPrice(@RequestParam ("file") String filename,
                                          @RequestParam ("city") City city)
    {

        return "Load Price DONE!";
    }

    @RequestMapping(value = "/loadprice", method = RequestMethod.POST)
    public ModelAndView loadprice (@RequestParam("file") MultipartFile file, @ModelAttribute ("user") User user,
                                   Model model , @ModelAttribute ("citych") City citych) {
        String name = null;
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
        model.addAttribute("city2" , resultCity);

        if (!file.isEmpty()) {
            try {



                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                String rootPath = "C:\\path\\"; //try also "C:\path\"
                File dir = new File(rootPath + File.separator + "loadFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                name = name.concat(" Uploaded");
                model.addAttribute("filename" , name);

                return new ModelAndView("load" , "user", user);

            } catch (Exception e) {
                model.addAttribute("filename", new String("FAIL"));
                return new ModelAndView("load" ,"user", user);
            }
        } else {
            model.addAttribute("filename", new String("FILE EMPTY"));
            return new ModelAndView("load" , "user", user);
        }
    }
}
