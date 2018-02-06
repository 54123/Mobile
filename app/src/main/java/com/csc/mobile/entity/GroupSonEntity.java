package com.csc.mobile.entity;

/**
 * 群组联系人实体类
 * Created by 随风 on 2018/1/30.
 */

public class GroupSonEntity {
    private String parentid;//群组id
    private String personnelid;//子id
    private String name;//联系人名字
    private String userphone;//联系人电话
    private String useremal;//联系人邮箱
    public String getParentid() {
        return parentid;
    }
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
    public String getPersonnelid() {
        return personnelid;
    }
    public void setPersonnelid(String personnelid) {
        this.personnelid = personnelid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserphone() {
        return userphone;
    }
    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }
    public String getUseremal() {
        return useremal;
    }
    public void setUseremal(String useremal) {
        this.useremal = useremal;
    }

    public GroupSonEntity(String parentid,String personnelid,String name,String userphone,String useremal) {
        super();
        this.parentid = parentid;
        this.personnelid = personnelid;
        this.name = name;
        this.userphone = userphone;
        this.useremal = useremal;
    }
}
