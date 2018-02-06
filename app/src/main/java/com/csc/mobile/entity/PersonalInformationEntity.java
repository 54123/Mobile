package com.csc.mobile.entity;

/**
 * 个人信息实体类
 * Created by 随风 on 2018/1/30.
 */

public class PersonalInformationEntity {
    private String ID; //用户ID
    private String NAME; //用户姓名
    private String DISPNAME; //
    private String TEL;
    private String DEPID;
    private String DEPNAME;
    private String ticket;

    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public String getNAME() {
        return NAME;
    }
    public void setNAME(String nAME) {
        NAME = nAME;
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
    public String getDEPID() {
        return DEPID;
    }
    public void setDEPID(String dEPID) {
        DEPID = dEPID;
    }
    public String getDEPNAME() {
        return DEPNAME;
    }
    public void setDEPNAME(String dEPNAME) {
        DEPNAME = dEPNAME;
    }
}
