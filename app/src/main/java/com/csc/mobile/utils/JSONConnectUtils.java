package com.csc.mobile.utils;

import android.content.Context;

import com.csc.mobile.entity.MainBottomEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON解析公共类
 * Created by 随风 on 2018/1/31.
 */

public class JSONConnectUtils {
    /**
     * json解析公共类
     */
    Context context;
    public JSONConnectUtils(Context context){
        this.context = context;
    }
/*
    //获取部门和人员信息
    public void personnelinformation(String parentid){
        parentids = parentid;
        Thread1 thread1 = new Thread1(1);
        thread1.start();
        try {
            thread1.join();
            if (contents != null && (!contents.equals(""))) {
                try {
                    JSONArray json = new JSONArray(contents);
                    departmentStafflist = new ArrayList<DepartmentStaffEntity>();
                    for(int i = 0; i < json.length(); i++){
                        JSONObject jsonObject = (JSONObject) json.get(i);
                        departmentStaffEntity = new DepartmentStaffEntity();
                        departmentStaffEntity.setFLDID(jsonObject.getString("FLDID"));
                        departmentStaffEntity.setFLDNAME(jsonObject.getString("FLDNAME"));
                        departmentStaffEntity.setFLDPARENT_ID(jsonObject.getString("FLDPARENT_ID"));
                        departmentStaffEntity.setTEL(jsonObject.getString("TEL"));
                        departmentStaffEntity.setMAIL(jsonObject.getString("MAIL"));
                        departmentStaffEntity.setFLDTYPE(jsonObject.getString("FLDTYPE"));
                        departmentStaffEntity.setUSERPY(jsonObject.getString("USERPY"));
                        departmentStafflist.add(departmentStaffEntity);
                    }
                    basicDateEntity.setDepartmentStafflist(departmentStafflist);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    */
/**
     * 获取反馈信息
     *//*

    public void getfeedback(String userid){
        userids = userid;
        Thread1 thread1 = new Thread1(0);
        thread1.start();
        try {
            thread1.join();
            if (contents != null && (!contents.equals(""))) {
                try {
                    JSONArray json = new JSONArray(contents);
                    liststring = new ArrayList<FeedbackContentEntity>();
                    for(int i = 0; i < json.length(); i++){
                        JSONObject jsonObject = (JSONObject) json.get(i);
                        feedbackContentEntity = new FeedbackContentEntity();
                        feedbackContentEntity.setFLDID(jsonObject.getString("FLDID"));
                        feedbackContentEntity.setFLDTAG(jsonObject.getString("FLDTAG"));
                        feedbackContentEntity.setFLDUSERID(jsonObject.getString("FLDUSERID"));
                        feedbackContentEntity.setFLDUSERTAG(jsonObject.getString("FLDUSERTAG"));
                        feedbackContentEntity.setFLDTYPE(jsonObject.getString("FLDTYPE"));
                        feedbackContentEntity.setFLDCONTENT(jsonObject.getString("FLDCONTENT"));
                        feedbackContentEntity.setFLDTIME(jsonObject.getString("FLDTIME"));
                        liststring.add(feedbackContentEntity);
                    }
                    basicDateEntity.setListstring(liststring);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
    private DatabaseHelper helper;
*/


    /**
     * 解析首界面底部按钮的json数据
     * @param str
     * @return
     */
    public static List<MainBottomEntity> getMainFragment(String str){
        List<MainBottomEntity> list = new ArrayList<MainBottomEntity>();
        try {
            JSONArray ja = new JSONArray(str);
            for (int i = 0; i < ja.length(); i++) {
                MainBottomEntity mainFragment = new MainBottomEntity();
                JSONObject jo = ja.getJSONObject(i);
                mainFragment.setFLDID(jo.getString("FLDID"));
                mainFragment.setFLDTAG(jo.getString("FLDTAG"));
                mainFragment.setFLDTEXT(jo.getString("FLDTEXT"));
                mainFragment.setFLDICON(jo.getString("FLDICON"));
                mainFragment.setFLDICONSEL(jo.getString("FLDICONSEL"));
                mainFragment.setFLDORDER(jo.getInt("FLDORDER"));
                mainFragment.setFLDTYPE(jo.getString("FLDTYPE"));
                mainFragment.setFLDTARGET(jo.getString("FLDTARGET"));
                mainFragment.setFLDVISIBLE(jo.getString("FLDVISIBLE"));
                mainFragment.setFLDREMARK(jo.getString("FLDREMARK"));
                list.add(mainFragment);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

   /* *//**
     * 解析我的设置的json数据
     *//*
    public static List<MineEntity> getMineList(String str){
        List<MineEntity> list = new ArrayList<MineEntity>();
        try {
            JSONArray ja = new JSONArray(str);
            for (int i = 0; i < ja.length(); i++) {
                MineEntity mainFragment = new MineEntity();
                JSONObject jo = ja.getJSONObject(i);
                mainFragment.setFLDID(jo.getString("FLDID"));
                mainFragment.setFLDTAG(jo.getString("FLDTAG"));
                mainFragment.setFLDTEXT(jo.getString("FLDTEXT"));
                mainFragment.setFLDICON(jo.getString("FLDICON"));
                mainFragment.setFLDORDER(jo.getInt("FLDORDER"));
                mainFragment.setFLDGROUP(jo.getString("FLDGROUP"));
                mainFragment.setFLDGROUPORDER(jo.getString("FLDGROUPORDER"));
                mainFragment.setFLDTYPE(jo.getString("FLDTYPE"));
                mainFragment.setFLDTARGET(jo.getString("FLDTARGET"));
                mainFragment.setFLDVISIBLE(jo.getString("FLDVISIBLE"));
                mainFragment.setFLDREMARK(jo.getString("FLDREMARK"));
                list.add(mainFragment);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    //消息
    public void messages(){
        Thread1 thread1 = new Thread1(2);
        thread1.start();
        try {
            thread1.join();
            if (contents != null && (!contents.equals(""))) {
                try {
                    JSONArray json = new JSONArray(contents);
                    messageList = new ArrayList<MessageEntity>();
                    for(int i = 0; i < json.length(); i++){
                        JSONObject jsonObject = (JSONObject) json.get(i);
                        messageEntity = new MessageEntity(null,null,null,null,null,null,null,null,null);
                        messageEntity.setFLDID(jsonObject.getString("FLDID"));
                        messageEntity.setFLDTITLE(jsonObject.getString("FLDTITLE"));
                        messageEntity.setFLDTEXT(jsonObject.getString("FLDTEXT"));
                        messageEntity.setFLDPICTURE(jsonObject.getString("FLDPICTURE"));
                        messageEntity.setFLDBEGINTIME(jsonObject.getString("FLDBEGINTIME"));
                        messageEntity.setFLDPUBLISHER(jsonObject.getString("FLDPUBLISHER"));
                        messageEntity.setFLDURL(jsonObject.getString("FLDURL"));
                        messageEntity.setTime(jsonObject.getString("FLDBEGINTIME"));
                        messageList.add(messageEntity);
                    }
                    basicDateEntity.setMessageList(messageList);
                    insertmessge();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
    //将系统消息数据插入到本地数据库
    public void insertmessge(){
        if(messageList != null || messageList.size() != 0){
            helper = new DatabaseHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();
            //插入系统消息
            for (int i = 0; i < messageList.size(); i++) {
                ContentValues valuse = new ContentValues();
                valuse.put("userid", basicDateEntity.getPersonalInformationList().get(0).getID());
                valuse.put("FLDID", messageList.get(i).getFLDID());
                valuse.put("FLDTITLE", messageList.get(i).getFLDTITLE());
                valuse.put("FLDTEXT", messageList.get(i).getFLDTEXT());
                valuse.put("FLDPICTURE", messageList.get(i).getFLDPICTURE());
                valuse.put("FLDBEGINTIME", messageList.get(i).getFLDBEGINTIME());
                valuse.put("FLDPUBLISHER", messageList.get(i).getFLDPUBLISHER());
                valuse.put("FLDURL", messageList.get(i).getFLDURL());
                valuse.put("isshow", "0");
                valuse.put("time", messageList.get(i).getTime());
                db.insert(helper.NAME_MESSAGE_TAL,null, valuse);
            }
            db.close();
        }
    }

    *//**
     * 解析负责人所负责的资源
     *//*
    public static List<PersonLiable> getLiableList(String jsonString){
        List<PersonLiable> list = new ArrayList<PersonLiable>();
        try {
            JSONArray ja = new JSONArray(jsonString);
            for (int i = 0; i < ja.length(); i++) {
                PersonLiable p = new PersonLiable();
                JSONObject jo = ja.getJSONObject(i);
                p.setFLDRESID(jo.getString("FLDRESID"));
                p.setFLDRESTAG(jo.getString("FLDRESTAG"));
                list.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    *//**
     * 解析勘误后资源的关联资源
     *//*
    public static List<PersonLiable> getAssociationDateList(String jsonString){
        List<PersonLiable> list = new ArrayList<PersonLiable>();
        try {
            JSONArray ja = new JSONArray(jsonString);
            for (int i = 0; i < ja.length(); i++) {
                PersonLiable p = new PersonLiable();
                JSONObject jo = ja.getJSONObject(i);
                p.setFLDRESID(jo.getString("fldId"));
                p.setFLDRESTAG(jo.getString("layerKey"));
                list.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
*/
}
