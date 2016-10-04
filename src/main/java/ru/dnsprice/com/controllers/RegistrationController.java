package ru.dnsprice.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping (value = "/final", method = RequestMethod.POST)
    public String regPost(@ModelAttribute ("user") User user) {
        userService.addContact(user);
        return "final"; //временная заглушка
    }
}
