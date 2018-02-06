package com.csc.mobile.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库助手类
 * Created by 随风 on 2018/1/30.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    // 数据库文件名
    public final static String DB_NAME = "my_database.db";
    // 数据库版本号
    public final static int DB_VERSION = 6;
    // 最初数据库版本号
    public final static int FIRST_DB_VERSION = 1;
    // 数据库表名
    public final static String NAME_COLLECTION_TAL = "name_collection";//收藏表
    public final static String NAME_COLLECT_COORD_TAL = "name_collect_coord";//坐标采集表
    public final static String NAME_ANNOTATION_POINT_TAL = "name_annotation_point";//标注点表
    public final static String NAME_HISTORY_TAL = "records";//历史记录表
    public final static String NAME_GROUP_TAL = "name_group";//我的群组表
    public final static String NAME_GROUP_PERSON_TAL = "name_groupperson";//群组对应的人员表
    public final static String NAME_MOBILE_TAL = "mobile_cache";//通讯录表
    public final static String NAME_MOBILE_PERSON_TAL = "mobile_person_cache";//通讯录人员缓存表
    public final static String NAME_MESSAGE_TAL = "message_tables" ;//系统消息表
    public final static String NAME_GESTRUE_TAL = "gestrue_tables" ;//手势密码
    public final static String NAME_USER_CONFIGURE = "name_user_configure"; //配置信息

    // 该构造方法用于创建数据库文件
    // context：上下文
    // name：数据库文件名
    // factory：游标工厂，如果null，则使用Android系统提供的默认游标
    // version：数据库版本号
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    // 该方法用于创建数据库中的表
    // db：代表已创建完成的数据库对象，可直接操作
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建表语句的意义：如果不存在则创建表，字段包括：主键_id, name, age
        //历史纪录
        //收藏
        String sql = "create table if not exists " + NAME_COLLECTION_TAL + "(_id integer primary key autoincrement,userid varchar, name varchar, flag integer, phone varchar,emal varchar)";

        String coordSql = "create table if not exists " + NAME_COLLECT_COORD_TAL + "(_id integer primary key autoincrement,time varchar, coord varchar, name varchar, lat varchar, lon varchar, type varchar)";

        String annotationSql = "create table if not exists " + NAME_ANNOTATION_POINT_TAL + "(_id integer primary key autoincrement,description varchar, lat varchar, lon varchar)";

        String recordSql = "create table if not exists "+ NAME_HISTORY_TAL +"(id integer primary key autoincrement,name varchar(200))";

        // 执行创建表的数据库语句
        db.execSQL(sql);
        db.execSQL(coordSql);
        db.execSQL(annotationSql);
        db.execSQL(recordSql);
        onUpgrade(db, FIRST_DB_VERSION, DB_VERSION);//
    }

    // 当数据库版本更新时，执行该方法
    // oldVersion：旧版本
    // newVersion：新版本
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion; i <= newVersion; i++) {
            switch (i) {
                case 2:
                    //版本升级到2时添加的语句
                    String recordSql = "create table if not exists "+ NAME_GROUP_TAL +"(_id integer primary key autoincrement,groupid varchar,name varchar)";
                    String grouppersonnelSql = "create table if not exists "+ NAME_GROUP_PERSON_TAL +"(_id integer primary key autoincrement,parentid varchar,personnelid varchar,name varchar,phone varchar,emal varchar)";

                    db.execSQL(recordSql);
                    db.execSQL(grouppersonnelSql);

                    break;
                case 3:
                    //版本升级到3时添加的语句......
                    String mobilelistSql = "create table if not exists " + NAME_MOBILE_TAL + "(_id integer primary key autoincrement,FLDID varchar,FLDNAME varchar,FLDREMARK varchar,FLDPARENT_ID varchar,FLDTAG varchar)";
                    String mobilepersonlistSql = "create table if not exists " + NAME_MOBILE_PERSON_TAL + "(_id integer primary key autoincrement,USERID varchar,DISPNAME varchar,USERPY varchar,TEL varchar,MAIL varchar,FLDPARENT_ID varchar)";

                    db.execSQL(mobilelistSql);
                    db.execSQL(mobilepersonlistSql);
                    break;
                case 4:
                    String messageSql = "create table if not exists " + NAME_MESSAGE_TAL + "(_id integer primary key autoincrement,userid varchar,FLDID varchar, FLDTITLE varchar,FLDTEXT varchar,FLDPICTURE varchar,FLDBEGINTIME varchar,FLDPUBLISHER varchar,FLDURL varchar,isshow varchar,time varchar)";
                    db.execSQL(messageSql);
                    break;
                case 5:
                    String gestrueSql = "create table if not exists " + NAME_GESTRUE_TAL + "(_id integer primary key autoincrement,userId varchar,gesturepassword varchar,username varchar,userpasword varchar)";
                    db.execSQL(gestrueSql);
                    break;
                case 6:
                    //0--关闭  1--开启
                    String configureSql = "create table if not exists " + NAME_USER_CONFIGURE + "(_id integer primary key autoincrement,userId varchar,apkCache varchar,phoneCache varchar,getMessage varchar,messageTime varchar,cacheTime varchar,cache varchar)";
                    db.execSQL(configureSql);

                    String addCollectUserIdSql = "alter table "+NAME_COLLECT_COORD_TAL + " add userId varchar(20)";
                    db.execSQL(addCollectUserIdSql);
                    break;
                default:
                    break;
            }
        }
    }
}
