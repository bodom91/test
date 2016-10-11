package ru.dnsprice.com.model;

import javax.persistence.*;

/**
 * Created by shestakov.m on 10.10.2016.
 */
@Entity
@Table(name = "usercity")
public class UserCity {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private Integer city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
