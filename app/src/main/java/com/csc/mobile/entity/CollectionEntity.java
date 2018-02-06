package com.csc.mobile.entity;

/**
 * 收藏实体类
 * Created by 随风 on 2018/1/30.
 */

public class CollectionEntity {
    private String userid;
    private String name;
    private String flag;
    private String phone;
    private String emal;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmal() {
        return emal;
    }
    public void setEmal(String emal) {
        this.emal = emal;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public CollectionEntity(String userid,String name, String flag, String phone, String emal) {
        super();
        this.userid =  userid;
        this.name = name;
        this.flag = flag;
        this.phone = phone;
        this.emal = emal;
    }
}
