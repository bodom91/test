package ru.dnsprice.com.check;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by shestakov.m on 04.10.2016.
 */
public class check extends BCryptPasswordEncoder {

    public static void main(String[] args) {
        check ch = new check();
        String f = ch.encode("2717843");
        System.out.println(f);
        System.out.println(ch.matches("sdfsdfsdf","$2a$10$PyWrsYmH5CiaXgqQsfbYk.Q3isfg/LHi17.5cusVtJYA0SPz8zps6"));

    }
}
