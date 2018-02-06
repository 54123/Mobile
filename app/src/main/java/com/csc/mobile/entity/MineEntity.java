package com.csc.mobile.entity;

/**
 * Created by 随风 on 2018/1/31.
 */

public class MineEntity {
    private String FLDID;//id
    private String FLDTAG;//tag
    private String FLDTEXT;//textview字体
    private String FLDICON;//ImageView图片
    private int FLDORDER;//组内item的序号
    private String FLDGROUP;//组名
    private String FLDGROUPORDER;//组的序号
    private String FLDTYPE;//类型   1--h5界面  2---原生界面
    private String FLDTARGET;//界面URL
    private String FLDVISIBLE;//*是否显示  1---显示  ！1---隐藏
    private String FLDREMARK;
    public String getFLDID() {
        return FLDID;
    }
    public void setFLDID(String fLDID) {
        FLDID = fLDID;
    }
    public String getFLDTAG() {
        return FLDTAG;
    }
    public void setFLDTAG(String fLDTAG) {
        FLDTAG = fLDTAG;
    }
    public String getFLDTEXT() {
        return FLDTEXT;
    }
    public void setFLDTEXT(String fLDTEXT) {
        FLDTEXT = fLDTEXT;
    }
    public String getFLDICON() {
        return FLDICON;
    }
    public void setFLDICON(String fLDICON) {
        FLDICON = fLDICON;
    }
    public int getFLDORDER() {
        return FLDORDER;
    }
    public void setFLDORDER(int fLDORDER) {
        FLDORDER = fLDORDER;
    }
    public String getFLDGROUP() {
        return FLDGROUP;
    }
    public void setFLDGROUP(String fLDGROUP) {
        FLDGROUP = fLDGROUP;
    }
    public String getFLDGROUPORDER() {
        return FLDGROUPORDER;
    }
    public void setFLDGROUPORDER(String fLDGROUPORDER) {
        FLDGROUPORDER = fLDGROUPORDER;
    }
    public String getFLDTYPE() {
        return FLDTYPE;
    }
    public void setFLDTYPE(String fLDTYPE) {
        FLDTYPE = fLDTYPE;
    }
    public String getFLDTARGET() {
        return FLDTARGET;
    }
    public void setFLDTARGET(String fLDTARGET) {
        FLDTARGET = fLDTARGET;
    }
    public String getFLDVISIBLE() {
        return FLDVISIBLE;
    }
    public void setFLDVISIBLE(String fLDVISIBLE) {
        FLDVISIBLE = fLDVISIBLE;
    }
    public String getFLDREMARK() {
        return FLDREMARK;
    }
    public void setFLDREMARK(String fLDREMARK) {
        FLDREMARK = fLDREMARK;
    }
}
