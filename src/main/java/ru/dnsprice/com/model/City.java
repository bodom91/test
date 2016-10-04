package ru.dnsprice.com.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Максим on 05.10.2016.
 */
@Entity
@Table(name = "city")
@JsonAutoDetect
public class City {

    @Id
    @Column (name = "id")
    private int id;

    @Column (name = "domain")
    private String domain;

    @Column (name = "state")
    private int state;

    @Column (name = "stateCpa")
    private String stateCpa;

    @CollectionOfElements(fetch = FetchType.EAGER)
    @JoinTable (name = "statereason", joinColumns = @JoinColumn(name = "id"))
    @Column (name = "stateReasons")
    private List<String> stateReasons;

    @CollectionOfElements
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable (name = "statereasoncpa", joinColumns = @JoinColumn(name = "id"))
    @Column (name = "stateReasonsCpa")
    private List<String> stateReasonsCpa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateCpa() {
        return stateCpa;
    }

    public void setStateCpa(String stateCpa) {
        this.stateCpa = stateCpa;
    }

    public List<String> getStateReasons() {
        return stateReasons;
    }

    public void setStateReasons(List<String> stateReasons) {
        this.stateReasons = stateReasons;
    }

    public List<String> getStateReasonsCpa() {
        return stateReasonsCpa;
    }

    public void setStateReasonsCpa(List<String> stateReasonsCpa) {
        this.stateReasonsCpa = stateReasonsCpa;
    }
}
