package com.csc.mobile.base;

/**
 * Created by 随风 on 2018/1/29.
 */

public class Constants {

    public static boolean debug = true;//是否打印Log日志

    public static boolean refreshCategory = false;//刷新分类页面
    public static String targetUrl = "";//Fragment是h5时的url字段

    public static boolean isDeleteResurce = false;//是否有删除资源操作
    public static String id = "";//删除资源的ID
    public static String tag = "";//删除资源的TAG

    //手势密码点的状态
    public static final int POINT_STATE_NORMAL = 0; // 正常状态
    public static final int POINT_STATE_SELECTED = 1; // 按下状态
    public static final int POINT_STATE_WRONG = 2; // 错误状态


    public static String PERSONLIABLE = "";//该用户是否是责任人  1--是
    public static String RESPONSIBLEMAN = "";//该用户是否是一线维护人
    public static String USERID = "";//用户id

    public static final String ApkDownloadPath = "/TJMobile/ApkDownload/";//APK下载路径
    public static final String MapDownloadPath = "/TJMobile/MapDownload/";//地图下载路径
    public static final String VideoDownloadPath= "/TJMobile/VideoDownload/";//视频下载路径
    public static final String ImageDownloadPath= "/TJMobile/ImageDownload/";//图片下载路径
    public static final String VideoCameraPath = "/TJMobile/VideoCamera/";//视频拍摄路径
    public static final String ImageCameraPath = "/TJMobile/ImageCamera/";//图片拍摄路径


    public static int TJ_PUBLISH = 0; //天津测试改为1；天津生产为2；

    public final static double longitudeOFFSET = (TJ_PUBLISH==1 || TJ_PUBLISH==2) ? 0.0:2.78;//经度偏移量
    public final static double latitudeOFFSET = (TJ_PUBLISH==1 || TJ_PUBLISH==2) ? 0.0:1.12;//纬度偏移量
    public final static String login_ip = TJ_PUBLISH==1 ? "10.142.1.166":TJ_PUBLISH==2 ? "117.131.225.146":"192.168.1.201";//IP地址
    public final static String login_port = TJ_PUBLISH==1 ? "9091":TJ_PUBLISH==2 ? "9084":"8081";//端口号
    public final static String login_name = TJ_PUBLISH==1 ? "MTMobileTJ":TJ_PUBLISH==2 ? "entity-android":"entity-android";//应用名称

    public final static double xmin = TJ_PUBLISH==0 ? 443903.708445:462915.716;
    public final static double ymin = TJ_PUBLISH==0 ? 4251154.584825:4271157.281;
    public final static double xmax = TJ_PUBLISH==0 ? 622133.411855:605129.542;
    public final static double ymax = TJ_PUBLISH==0 ? 4477776.192675:4458681.094;


    //程序使用接口
    public static final String LOGIN = "/sys/login.do";//登录接口
    public final static String LOAD_MAIN_FRAGMENT_URL = "/mine/loadmainfragment.do";//主页面底部按钮数据接口
}
