package com.csc.mobile.entity;

/**
 * 我的群组实体类
 * Created by 随风 on 2018/1/30.
 */

public class MineGroupEntity {
    private String groupid;//群组id
    private String mygroupname;//群组名称

    public String getGroupid() {
        return groupid;
    }
    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
    public String getMygroupname() {
        return mygroupname;
    }
    public void setMygroupname(String mygroupname) {
        this.mygroupname = mygroupname;
    }
    public MineGroupEntity(String name,String groupid) {
        super();
        this.mygroupname = name;
        this.groupid = groupid;
    }
}
