package ru.dnsprice.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.service.UserService;

import javax.annotation.Resource;

/**
 * Created by shestakov.m on 04.10.2016.
 */

@Controller
public class RegistrationController {

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping (value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        return new ModelAndView("registration", "user2", new User());
    }

    @RequestMapping (value = "/registration", method = RequestMethod.POST)
    public ModelAndView regPost(@ModelAttribute ("user2") User user) {
        userService.addContact(user);
        return new ModelAndView("final"); //временная заглушка
    }
}
