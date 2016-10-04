package ru.dnsprice.com.model;

import javax.persistence.*;

/**
 * Created by shestakov.m on 03.10.2016.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column (name = "userid")
    @GeneratedValue
    private int userid;


    @Column (name = "name")
    private String name;

    @Column (name = "password")
    private String password;

    @Column (name = "email")
    private String email;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
