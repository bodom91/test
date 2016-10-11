package ru.dnsprice.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.dnsprice.com.model.User;

/**
 * Created by shestakov.m on 05.10.2016.
 */

@Controller
@SessionAttributes("user")
public class ContactController {

    @ModelAttribute
    public User createUser(){
        return new User();
    }

    @RequestMapping (value = "/contact", method = RequestMethod.GET)
    public ModelAndView contact(@ModelAttribute ("user") User user) {
        if (user.getUserid() == 0) {
            return new ModelAndView("/error/403");
        }
        return new ModelAndView("contact", "user", user);
    }
}
