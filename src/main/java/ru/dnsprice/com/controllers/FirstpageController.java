package ru.dnsprice.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.service.UserService;

import javax.annotation.Resource;

/**
 * Created by shestakov.m on 03.10.2016.
 */

@Controller
public class FirstpageController {

    @Resource (name = "userService")
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView firstPage() {
        return new ModelAndView("login","user", new User());
    }

    @RequestMapping(value = "/good", method = RequestMethod.POST)
    public ModelAndView logincheck(@ModelAttribute("user") User user) {
        if (userService.checkUser(user)) {
            return new ModelAndView("good", "user", user);
        } else return new ModelAndView("fail");
    }


}
