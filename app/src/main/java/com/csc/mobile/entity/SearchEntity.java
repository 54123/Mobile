package com.csc.mobile.entity;

/**
 * 搜索实体类
 * Created by 随风 on 2018/1/30.
 */

public class SearchEntity {
    private String USERID;
    private String DISPNAME;
    private String USERPY;
    private String TEL;
    private String MAIL;
    private String FLDPARENT_ID;
    private boolean isselect;
    public String getUSERID() {
        return USERID;
    }
    public void setUSERID(String uSERID) {
        USERID = uSERID;
    }
    public String getDISPNAME() {
        return DISPNAME;
    }
    public void setDISPNAME(String dISPNAME) {
        DISPNAME = dISPNAME;
    }
    public String getTEL() {
        return TEL;
    }
    public void setTEL(String tEL) {
        TEL = tEL;
    }
    public String getMAIL() {
        return MAIL;
    }
    public void setMAIL(String mAIL) {
        MAIL = mAIL;
    }
    public boolean isIsselect() {
        return isselect;
    }
    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }
    public String getUSERPY() {
        return USERPY;
    }
    public void setUSERPY(String uSERPY) {
        USERPY = uSERPY;
    }

    public String getFLDPARENT_ID() {
        return FLDPARENT_ID;
    }
    public void setFLDPARENT_ID(String fLDPARENT_ID) {
        FLDPARENT_ID = fLDPARENT_ID;
    }
    public SearchEntity(String USERID,String DISPNAME,String USERPY,String TEL,String MAIL,String FLDPARENT_ID) {
        super();
        this.USERID = USERID;
        this.DISPNAME = DISPNAME;
        this.USERPY = USERPY;
        this.TEL = TEL;
        this.MAIL = MAIL;
        this.FLDPARENT_ID = FLDPARENT_ID;
    }
}
