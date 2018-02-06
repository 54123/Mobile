package com.csc.mobile.db;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.WindowManager;

import com.csc.mobile.entity.BasicDateEntity;
import com.csc.mobile.entity.CollectionEntity;
import com.csc.mobile.entity.GestrueEntity;
import com.csc.mobile.entity.GroupSonEntity;
import com.csc.mobile.entity.MessageEntity;
import com.csc.mobile.entity.MineGroupEntity;
import com.csc.mobile.entity.OrganizationEntity;
import com.csc.mobile.entity.SearchEntity;
import com.csc.mobile.entity.UserConfigureEntity;
import com.csc.mobile.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by 随风 on 2018/1/30.
 */

public class Countents {
    Context context;
    List<CollectionEntity> collectionnamelists;//我的收藏集合
    List<MineGroupEntity> mineGrouplists;//我的群组集合
    List<GroupSonEntity> groupSonlists;//我的群组子集合
    List<OrganizationEntity> mobileTabellist;//通讯录部门
    List<SearchEntity> searchList;//通讯录人员
    List<MessageEntity> messageLists;//系统消息
    List<GestrueEntity> gestrueList;//手势密码集合
    List<UserConfigureEntity> userConfigureLists;//用户配置集合
    private static DatabaseHelper helper;
    public static SQLiteDatabase db;
    public static Cursor cursor;
    private String userids;
    private String name;
    private String flag;
    private String phone;
    private String emals;
    private String groupname;
    private String groupid;
    private String parentids;
    private String personnelid;
    private String personnelname;
    BasicDateEntity basicDateEntity = BasicDateEntity.getSingle();
    private String userphone;
    private String useremal;
    public Countents(Context context){
        this.context = context;
    }
    //获取本地数据库中的数据
    public void SQLitedate(){
        collectionnamelists = new ArrayList<CollectionEntity>();
        helper = new DatabaseHelper(context);
        // 通过数据库助手，创建数据库
        db = helper.getReadableDatabase();
        cursor = db.query(DatabaseHelper.NAME_COLLECTION_TAL, null, null,null, null, null, null, null);
        // 字段对应的索引值
        int userid = cursor.getColumnIndex("userid");
        int nameIndex = cursor.getColumnIndex("name");
        int flagIndex = cursor.getColumnIndex("flag");
        int phoneIndex = cursor.getColumnIndex("phone");
        int emalIndex = cursor.getColumnIndex("emal");
        // 遍历查询数据
        while (cursor.moveToNext()) {
            // 通过索引值间接获取字段对应的数据
            userids = cursor.getString(userid);
            name = cursor.getString(nameIndex);
            flag = cursor.getString(flagIndex);
            phone = cursor.getString(phoneIndex);
            emals = cursor.getString(emalIndex);
            CollectionEntity collectionlist = new CollectionEntity(userids,name, flag, phone, emals);
            collectionnamelists.add(collectionlist);
        }
        basicDateEntity.setCollectionnamelist(collectionnamelists);
        db.close();
        cursor.close();
    }
    //获取本地我的群组数据库中的数据
    public void SQLitegroupdata(){
        mineGrouplists = new ArrayList<MineGroupEntity>();
        helper = new DatabaseHelper(context);
        // 通过数据库助手，创建数据库
        db = helper.getReadableDatabase();
        cursor = db.query(DatabaseHelper.NAME_GROUP_TAL, null, null,null, null, null, null, null);
        // 字段对应的索引值
        int nameIndex = cursor.getColumnIndex("name");
        int groupidIndex = cursor.getColumnIndex("groupid");
        // 遍历查询数据
        while (cursor.moveToNext()) {
            // 通过索引值间接获取字段对应的数据
            groupname = cursor.getString(nameIndex);
            groupid = cursor.getString(groupidIndex);
            MineGroupEntity mineGroupEntity = new MineGroupEntity(groupname,groupid);
            mineGrouplists.add(mineGroupEntity);
        }
        basicDateEntity.setMineGrouplist(mineGrouplists);
        db.close();
        cursor.close();
    }
    //获取本地我的群组中子数据库中的数据
    public void SQLitegrouppersondata(String parentid){
        groupSonlists = new ArrayList<GroupSonEntity>();
        helper = new DatabaseHelper(context);
        // 通过数据库助手，创建数据库
        db = helper.getReadableDatabase();
        cursor = db.query(DatabaseHelper.NAME_GROUP_PERSON_TAL, null, null,null, null, null, null, null);
        // 字段对应的索引值
        int parentidIndex = cursor.getColumnIndex("parentid");
        int personnelidIndex = cursor.getColumnIndex("personnelid");
        int personnelnameIndex = cursor.getColumnIndex("name");
        int phoneIndex = cursor.getColumnIndex("phone");
        int emalIndex = cursor.getColumnIndex("emal");
        // 遍历查询数据
        while (cursor.moveToNext()) {
            // 通过索引值间接获取字段对应的数据
            parentids = cursor.getString(parentidIndex);
            if(parentids.equals(parentid)){
                personnelid = cursor.getString(personnelidIndex);
                personnelname = cursor.getString(personnelnameIndex);
                userphone = cursor.getString(phoneIndex);
                useremal = cursor.getString(emalIndex);
                GroupSonEntity groupSonEntity = new GroupSonEntity(parentid,personnelid,personnelname,userphone,useremal);
                groupSonlists.add(groupSonEntity);
            }
        }
        basicDateEntity.setGroupSonlist(groupSonlists);
        db.close();
        cursor.close();
    }

    //获取本地数据库中通讯录所有部门的数据
    public void SQLitedate_mobile(){
        mobileTabellist = new ArrayList<OrganizationEntity>();
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        cursor = db.query(DatabaseHelper.NAME_MOBILE_TAL, null, null,null, null, null, null, null);
        int FLDIDIndex = cursor.getColumnIndex("FLDID");
        int FLDNAMEIndex = cursor.getColumnIndex("FLDNAME");
        int FLDREMARKIndex = cursor.getColumnIndex("FLDREMARK");
        int FLDPARENT_IDIndex = cursor.getColumnIndex("FLDPARENT_ID");
        int FLDTAGIndex = cursor.getColumnIndex("FLDTAG");
        while (cursor.moveToNext()) {
            String FLDID = cursor.getString(FLDIDIndex);
            String FLDNAME = cursor.getString(FLDNAMEIndex);
            String FLDREMARK = cursor.getString(FLDREMARKIndex);
            String FLDPARENT_ID = cursor.getString(FLDPARENT_IDIndex);
            String FLDTAG = cursor.getString(FLDTAGIndex);
            OrganizationEntity mobileTabelEntity = new OrganizationEntity(FLDID,FLDNAME, FLDREMARK, FLDPARENT_ID, FLDTAG);
            mobileTabellist.add(mobileTabelEntity);
        }
        basicDateEntity.setDepartmentlist(mobileTabellist);
        db.close();
        cursor.close();
    }
    //获取本地数据库中通讯录所有人员的数据
    public void SQLitedate_person(){
        searchList = new ArrayList<SearchEntity>();
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        cursor = db.query(DatabaseHelper.NAME_MOBILE_PERSON_TAL, null, null,null, null, null, null, null);
        int USERIDIndex = cursor.getColumnIndex("USERID");
        int DISPNAMEIndex = cursor.getColumnIndex("DISPNAME");
        int USERPYIndex = cursor.getColumnIndex("USERPY");
        int TELIndex = cursor.getColumnIndex("TEL");
        int MAILIndex = cursor.getColumnIndex("MAIL");
        int FLDPARENT_IDIndex = cursor.getColumnIndex("FLDPARENT_ID");
        while (cursor.moveToNext()) {
            String USERID = cursor.getString(USERIDIndex);
            String DISPNAME = cursor.getString(DISPNAMEIndex);
            String USERPY = cursor.getString(USERPYIndex);
            String TEL = cursor.getString(TELIndex);
            String MAIL = cursor.getString(MAILIndex);
            String FLDPARENT_ID = cursor.getString(FLDPARENT_IDIndex);
            SearchEntity searchEntity = new SearchEntity(USERID,DISPNAME, USERPY, TEL, MAIL,FLDPARENT_ID);
            searchList.add(searchEntity);
        }
        basicDateEntity.setPersonList(searchList);
        db.close();
        cursor.close();
    }
    //获取本地数据库中系统消息数据
    public void SQLitedate_message(){
        messageLists = new ArrayList<MessageEntity>();
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();

//		//按时间排序
//		Cursor cursors = db.rawQuery("select * from message_tables Order By FLDBEGINTIME Desc", null);
        //按用户id作为条件查询
        cursor = db.rawQuery("select * from message_tables where userid=?Order By FLDBEGINTIME Desc",new String[] { SharedPreferencesUtil.getValue(context, "ID", "")});

        if(cursor.getColumnCount()==0)
            return;
        int FLDIDIndex = cursor.getColumnIndex("FLDID");
        int FLDTITLEIndex = cursor.getColumnIndex("FLDTITLE");
        int FLDTEXTIndex = cursor.getColumnIndex("FLDTEXT");
        int FLDPICTUREIndex = cursor.getColumnIndex("FLDPICTURE");
        int FLDBEGINTIMEIndex = cursor.getColumnIndex("FLDBEGINTIME");
        int FLDPUBLISHERIndex = cursor.getColumnIndex("FLDPUBLISHER");
        int FLDURLIndex = cursor.getColumnIndex("FLDURL");
        int IsShowIndex = cursor.getColumnIndex("isshow");
        int timeIndex = cursor.getColumnIndex("time");
        while (cursor.moveToNext()) {
            String FLDID = cursor.getString(FLDIDIndex);
            String FLDTITLE = cursor.getString(FLDTITLEIndex);
            String FLDTEXT = cursor.getString(FLDTEXTIndex);
            String FLDPICTURE = cursor.getString(FLDPICTUREIndex);
            String FLDBEGINTIME = cursor.getString(FLDBEGINTIMEIndex);
            String FLDPUBLISHER = cursor.getString(FLDPUBLISHERIndex);
            String FLDURL = cursor.getString(FLDURLIndex);
            String IsShow = cursor.getString(IsShowIndex);
            String time = cursor.getString(timeIndex);

            MessageEntity messageEntity = new MessageEntity(FLDID,FLDTITLE,FLDTEXT, FLDPICTURE, FLDBEGINTIME,FLDPUBLISHER,FLDURL,IsShow,time);
            messageLists.add(messageEntity);
        }
        basicDateEntity.setMessageList(messageLists);
        db.close();
        cursor.close();
    }
    //获取本地数据库中用户对应的手势密码数据
    public void SQLitedate_gestrueDate(){
        gestrueList = new ArrayList<GestrueEntity>();
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        cursor = db.query(DatabaseHelper.NAME_GESTRUE_TAL, null, null,null, null, null, null, null);
        int USERIDIndex = cursor.getColumnIndex("userId");
        int GESTRUEPASSWORDIndex = cursor.getColumnIndex("gesturepassword");
        int usernameIndex = cursor.getColumnIndex("username");
        int userpasswordIndex = cursor.getColumnIndex("userpasword");
        Log.e("登录界面", "userpasswordIndex==" + userpasswordIndex);
        while (cursor.moveToNext()) {
            String USERID = cursor.getString(USERIDIndex);
            String GESTRUEPASSWORD = cursor.getString(GESTRUEPASSWORDIndex);
            String username = cursor.getString(usernameIndex);
            String userpassword = cursor.getString(userpasswordIndex);
            GestrueEntity gestrueEntity = new GestrueEntity(USERID,GESTRUEPASSWORD,username,userpassword);
            Log.e("登录界面", "userpasswordIndex==" + gestrueEntity.toString());
            gestrueList.add(gestrueEntity);
        }
        basicDateEntity.setGestruelists(gestrueList);
        db.close();
        cursor.close();
    }

    /**
     * 密码修改后，将新密码更改到手势表中
     * @param pwd
     * @param userid
     * @param content
     */
    public static void updatePwd(String pwd,String userid,Activity content){
        ContentValues values = new ContentValues();
        values.put("userpasword", pwd);
        DatabaseHelper dbhelper = new DatabaseHelper(content);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.update(DatabaseHelper.NAME_GESTRUE_TAL, values, "userId=?", new String[]{userid});
        db.close();
    }

    /**
     * 获取本地数据库中用户配置数据
     */
    public List<UserConfigureEntity> getSqlUserConfigureDate(){
        userConfigureLists = new ArrayList<UserConfigureEntity>();
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        cursor = db.query(DatabaseHelper.NAME_USER_CONFIGURE, null, null,null, null, null, null, null);
        int userId = cursor.getColumnIndex("userId");
        int apkCache = cursor.getColumnIndex("apkCache");
        int phoneCache = cursor.getColumnIndex("phoneCache");
        int getMessage = cursor.getColumnIndex("getMessage");
        int messageTime = cursor.getColumnIndex("messageTime");
        int cacheTime = cursor.getColumnIndex("cacheTime");
        int cache = cursor.getColumnIndex("cache");
        while (cursor.moveToNext()) {
            UserConfigureEntity config = new UserConfigureEntity(
                    cursor.getString(userId), cursor.getString(apkCache),
                    cursor.getString(phoneCache), cursor.getString(getMessage),
                    cursor.getString(messageTime), cursor.getString(cacheTime),
                    cursor.getString(cache));
            userConfigureLists.add(config);
        }
        db.close();
        cursor.close();
        return userConfigureLists;
    }

    /**
     * 判断表是否存在
     *
     * @return
     */
    public boolean IsTableExist() {
        boolean isTableExist = false;
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from sqlite_master where name='"+DatabaseHelper.NAME_USER_CONFIGURE+"'",null);
        if(c.getCount()!=0){
            isTableExist = true;
        }
        c.close();
        db.close();
        return isTableExist;
    }

    //设置背景亮度
    public void backgroundAlphas(float bgAlpha){
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            //不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
            ((Activity) context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            //此行代码主要是解决在华为手机上半透明效果无效的bug
            ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        ((Activity) context).getWindow().setAttributes(lp);
    }
}
