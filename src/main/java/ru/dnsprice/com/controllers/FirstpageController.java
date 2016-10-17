package ru.dnsprice.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dnsprice.com.model.User;
import ru.dnsprice.com.service.UserService;

import javax.annotation.Resource;

/**
 * Created by shestakov.m on 03.10.2016.
 */
@Controller
@SessionAttributes(value = "user")
public class FirstpageController {

    @Resource (name = "userService")
    private UserService userService;

    @ModelAttribute
    public User createUser(){
        return new User();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView firstPage(@ModelAttribute ("user") User user) {
        if (user.getUserid() == 0) {
            return new ModelAndView("login", "user", new User());
        } else return new ModelAndView("/homepage" , "user" , user);
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.POST)
    public ModelAndView logincheck(@ModelAttribute("user") User user) {
        if (userService.checkUser(user)) {
            user = userService.getUserByName(user.getName());
            return new ModelAndView("homepage", "user", user);
        } else return new ModelAndView("/error/404");
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public ModelAndView homepage(@ModelAttribute("user") User user) {
        if (user.getUserid() == 0) {
            return new ModelAndView("/error/403" , "user" , user);
        } else return new ModelAndView("homepage" , "user" , user);

    }

}
