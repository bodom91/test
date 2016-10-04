package ru.dnsprice.com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by shestakov.m on 05.10.2016.
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "/error/404" , method = RequestMethod.GET)
    public String error() {
        return "/error/404";
    }

}
