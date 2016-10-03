package ru.dnsprice.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.dnsprice.com.model.User;

/**
 * Created by shestakov.m on 03.10.2016.
 */

@Controller
public class FirstpageController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView firstPage() {
        return new ModelAndView("login","user", new User());
    }

    @RequestMapping(value = "/good", method = RequestMethod.POST)
    public ModelAndView logincheck(@ModelAttribute("user") User user) {
        if (user.getName().equals("maxim@mail.ru") && user.getPassword().equals("55")) {
            return new ModelAndView("good", "user", user);
        } else return new ModelAndView("fail");
    }
}
