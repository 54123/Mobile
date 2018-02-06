package com.csc.mobile.entity;

import android.widget.Button;

/**
 * 首界面底部按钮实体类
 * Created by 随风 on 2018/1/31.
 */

public class MainBottomEntity {
    private String FLDID;//id
    private String FLDTAG;//tag
    /**底部按钮的文字 */
    private String FLDTEXT;//
    /**选中时按钮图片 */
    private String FLDICON;//
    /**默认底部按钮图片*/
    private String FLDICONSEL;//
    /**排序顺序*/
    private int FLDORDER; //
    /**类型  1--h5界面  2---原生界面*/
    private String FLDTYPE;//
    /**界面URL*/
    private String FLDTARGET;
    /**是否显示  1---显示  ！1---隐藏*/
    private String FLDVISIBLE;//
    private String FLDREMARK;//
    private Button button;
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
    public String getFLDICONSEL() {
        return FLDICONSEL;
    }
    public void setFLDICONSEL(String fLDICONSEL) {
        FLDICONSEL = fLDICONSEL;
    }
    public int getFLDORDER() {
        return FLDORDER;
    }
    public void setFLDORDER(int fLDORDER) {
        FLDORDER = fLDORDER;
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
