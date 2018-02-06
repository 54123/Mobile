package com.csc.mobile.entity;

/**
 * 部门人员实体类
 * Created by 随风 on 2018/1/30.
 */

public class DepartmentStaffEntity {
    private String sortLetters;  //显示数据拼音的首字母
    private String FLDID;
    private String FLDNAME;
    private String FLDPARENT_ID;
    private String TEL;
    private String MAIL;
    private String FLDTYPE;
    private String USERPY;
    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
    public String getFLDID() {
        return FLDID;
    }
    public void setFLDID(String fLDID) {
        FLDID = fLDID;
    }
    public String getFLDNAME() {
        return FLDNAME;
    }
    public void setFLDNAME(String fLDNAME) {
        FLDNAME = fLDNAME;
    }
    public String getFLDPARENT_ID() {
        return FLDPARENT_ID;
    }
    public void setFLDPARENT_ID(String fLDPARENT_ID) {
        FLDPARENT_ID = fLDPARENT_ID;
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
    public String getFLDTYPE() {
        return FLDTYPE;
    }
    public void setFLDTYPE(String fLDTYPE) {
        FLDTYPE = fLDTYPE;
    }
    public String getUSERPY() {
        return USERPY;
    }
    public void setUSERPY(String uSERPY) {
        USERPY = uSERPY;
    }
}
