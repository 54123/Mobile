package com.csc.mobile.entity;

/**
 * 系统消息实体类
 * Created by 随风 on 2018/1/30.
 */

public class MessageEntity {
    private String FLDID;
    private String FLDTITLE;
    private String FLDTEXT;
    private String FLDPICTURE;
    private String FLDBEGINTIME;//时间
    private String FLDPUBLISHER;
    private String FLDURL;
    private String isShow;
    private String time;
    private boolean isselect;

    public String getFLDID() {
        return FLDID;
    }

    public void setFLDID(String fLDID) {
        FLDID = fLDID;
    }

    public String getFLDTITLE() {
        return FLDTITLE;
    }

    public void setFLDTITLE(String fLDTITLE) {
        FLDTITLE = fLDTITLE;
    }

    public String getFLDTEXT() {
        return FLDTEXT;
    }

    public void setFLDTEXT(String fLDTEXT) {
        FLDTEXT = fLDTEXT;
    }

    public String getFLDPICTURE() {
        return FLDPICTURE;
    }

    public void setFLDPICTURE(String fLDPICTURE) {
        FLDPICTURE = fLDPICTURE;
    }

    public String getFLDBEGINTIME() {
        return FLDBEGINTIME;
    }

    public void setFLDBEGINTIME(String fLDBEGINTIME) {
        FLDBEGINTIME = fLDBEGINTIME;
    }


    public String getFLDPUBLISHER() {
        return FLDPUBLISHER;
    }

    public void setFLDPUBLISHER(String fLDPUBLISHER) {
        FLDPUBLISHER = fLDPUBLISHER;
    }

    public String getFLDURL() {
        return FLDURL;
    }

    public void setFLDURL(String fLDURL) {
        FLDURL = fLDURL;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isIsselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }


    public MessageEntity(String FLDID,String FLDTITLE,String FLDTEXT,String FLDPICTURE,String FLDBEGINTIME,String FLDPUBLISHER,String FLDURL,String isShow,String time){
        this.FLDID = FLDID;
        this.FLDTITLE = FLDTITLE;
        this.FLDTEXT = FLDTEXT;
        this.FLDPICTURE = FLDPICTURE;
        this.FLDBEGINTIME = FLDBEGINTIME;
        this.FLDPUBLISHER = FLDPUBLISHER;
        this.FLDURL = FLDURL;
        this.isShow = isShow;
        this.time = time;
    }
}
