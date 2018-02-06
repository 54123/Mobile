package com.csc.mobile.entity;

/**
 * 手势密码实体类
 * Created by 随风 on 2018/1/30.
 */

public class GestrueEntity {
    private String userid;
    private String gestruepassword;
    private String username;
    private String userpassword;

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getGestruepassword() {
        return gestruepassword;
    }
    public void setGestruepassword(String gestruepassword) {
        this.gestruepassword = gestruepassword;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserpassword() {
        return userpassword;
    }
    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
    public GestrueEntity(String userid,String gestruepassword,String username,String userpassword){
        this.userid = userid;
        this.gestruepassword = gestruepassword;
        this.username = username;
        this.userpassword = userpassword;
    }

    @Override
    public String toString() {
        return "GestrueEntity{" +
                "userid='" + userid + '\'' +
                ", gestruepassword='" + gestruepassword + '\'' +
                ", username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                '}';
    }
}
