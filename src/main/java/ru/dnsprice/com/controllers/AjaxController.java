package ru.dnsprice.com.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import ru.dnsprice.com.model.City;
import ru.dnsprice.com.model.Goods;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.model.UserCity;
import ru.dnsprice.com.service.CityService;
import ru.dnsprice.com.service.UserCityService;
import ru.dnsprice.com.utils.GetAvailableCity;
import ru.dnsprice.com.utils.api.Compains;
import ru.dnsprice.com.utils.logic.ReadXlsx;
import ru.dnsprice.com.utils.logic.ServiceClass;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Resource
    private GetAvailableCity getAvailableCity;

    @Resource
    private ServiceClass serviceClass;

    @Resource
    private ReadXlsx readXlsx;


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


    @RequestMapping(value = "/loadprice", method = RequestMethod.POST)
    public @ResponseBody String loadprice (@RequestParam("file") MultipartFile file,@RequestParam("city") String city , @ModelAttribute ("user") User user,
                                   Model model , @ModelAttribute ("citych") City citych) {
        String name = null;

        getAvailableCity.getCity(user, citych, model);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                String rootPath = "C:\\path\\";
                File dir = new File(rootPath + File.separator + "loadFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();

                ArrayList<Goods> listGoods = serviceClass.getAll(city);
                HashMap<String, String> ourPrice = readXlsx.Read(dir.getAbsolutePath() + File.separator + name);
                ArrayList<String> resultFile = serviceClass.equalsArray(ourPrice, listGoods);
                if (resultFile.size() < 500) {
                    serviceClass.loadPrice(city,"AQAAAAAW7HREAAND_oJ25b48yEpylzgd3Rjrzrk","9ad1fe90a69d4725bae74396f1b52ae8", resultFile);
                } else {
                    int count = (resultFile.size() / 500) + 1;
                    int countO = 0;
                    int countN = 0;
                    for (int i = 1; i <= count; i++) {
                        if (i == count) {
                            countO = countO + i * 500;
                            ArrayList<String> list = new ArrayList<String>();
                            list.addAll(resultFile.subList(countN, resultFile.size() - 1));
                            serviceClass.loadPrice(city,"AQAAAAAW7HREAAND_oJ25b48yEpylzgd3Rjrzrk","9ad1fe90a69d4725bae74396f1b52ae8", list);
                            countN += 500;
                        } else {
                            countO = countO + i * 500;
                            ArrayList<String> list = new ArrayList<String>();
                            list.addAll(resultFile.subList(countN, i * 500));
                            serviceClass.loadPrice(city,"AQAAAAAW7HREAAND_oJ25b48yEpylzgd3Rjrzrk","9ad1fe90a69d4725bae74396f1b52ae8", list);
                            countN += 500;
                        }
                    }
                }
                uploadedFile.delete();
                String result ="<i class=\"glyphicon glyphicon-ok \"></i>";
                return result;

            } catch (Exception e) {
                model.addAttribute("filename", new String("FAIL"));
                return "FAIL";
            }
        } else {
            model.addAttribute("filename", new String("FILE EMPTY"));
            return "FILE EMPTY";
        }
    }
}
