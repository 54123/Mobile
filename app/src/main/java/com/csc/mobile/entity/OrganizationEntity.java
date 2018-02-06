package com.csc.mobile.entity;

/**
 * 组织架构实体类
 * Created by 随风 on 2018/1/30.
 */

public class OrganizationEntity {
    private String FLDID;
    private String FLDNAME;
    private String FLDREMARK;
    private String FLDPARENT_ID;
    private String FLDTAG;
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
    public String getFLDREMARK() {
        return FLDREMARK;
    }
    public void setFLDREMARK(String fLDREMARK) {
        FLDREMARK = fLDREMARK;
    }
    public String getFLDPARENT_ID() {
        return FLDPARENT_ID;
    }
    public void setFLDPARENT_ID(String fLDPARENT_ID) {
        FLDPARENT_ID = fLDPARENT_ID;
    }
    public String getFLDTAG() {
        return FLDTAG;
    }
    public void setFLDTAG(String fLDTAG) {
        FLDTAG = fLDTAG;
    }
    public OrganizationEntity(String FLDID,String FLDNAME,String FLDREMARK,String FLDPARENT_ID,String FLDTAG) {
        super();
        this.FLDID = FLDID;
        this.FLDNAME = FLDNAME;
        this.FLDREMARK = FLDREMARK;
        this.FLDPARENT_ID = FLDPARENT_ID;
        this.FLDTAG = FLDTAG;
    }
}
