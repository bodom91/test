package ru.dnsprice.com.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by Максим on 09.10.2016.
 */
@JsonAutoDetect
public class Goods {
    private String name;
    private String url;
    private Float bid;
    private Float cbid;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Float getBid() {
        return bid;
    }

    public void setBid(Float bid) {
        this.bid = bid;
    }

    public Float getCbid() {
        return cbid;
    }

    public void setCbid(Float cbid) {
        this.cbid = cbid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
