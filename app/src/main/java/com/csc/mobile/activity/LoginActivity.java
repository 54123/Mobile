package com.csc.mobile.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.csc.mobile.R;
import com.csc.mobile.base.BaseActivity;
import com.csc.mobile.base.Constants;
import com.csc.mobile.db.Countents;
import com.csc.mobile.entity.BasicDateEntity;
import com.csc.mobile.entity.PersonalInformationEntity;
import com.csc.mobile.entity.UserConfigureEntity;
import com.csc.mobile.utils.SharedPreferencesUtil;
import com.csc.mobile.utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 随风 on 2018/1/29.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout layout_bottom;
    private EditText et_user,et_password,dialog_et_ip,dialog_et_port,dialog_et_name;
    private Button btn_login,dialog_btn_sumbit;
    private CheckBox cb_password,cb_save;
    private ImageView dialog_iv_close;

    private String ACTION_IP;//IP地址
    private String userName;//用户名
    private String userId;//用户ID
    private String password;//密码
    private String md5Password;//MD5加密后的密码
    private String gesPassword;//用户手势密码
    private String loginContents;//登录接口返回数据

    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;
    private AlertDialog ipDialog;

    private BasicDateEntity basicDateEntity = BasicDateEntity.getSingle();
    private List<PersonalInformationEntity> personalInformationList;
    private PersonalInformationEntity personalInformationEntity;//用户信息实体类

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 107){
                //获取用户名和密码   为了使在没有网的时候进入登录界面的时候输入框里有值
                mPreferences = getSharedPreferences("login", MODE_PRIVATE);
                String users =  mPreferences.getString("userid", "");
                String psws = mPreferences.getString("userpasswors", "");
                if(users.equals("")){
                    String userss = et_user.getText().toString();
                    String pswss = et_password.getText().toString();
                    et_user.setText(userss);
                    et_password.setText(pswss);
                }else{
                    et_user.setText(users);
                    et_password.setText(psws);
                }
                toastShort("服务器连接异常,请稍后再试！");
            }else if(msg.what == 109){
                toastShort("服务器连接异常,请稍后再试！");
            }
        }
    };

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPreferences = getSharedPreferences("login", MODE_PRIVATE);
        userName =  mPreferences.getString("userid", "");
        mEditor = mPreferences.edit();
        initViews();
    }

    private void initViews() {
        layout_bottom = (LinearLayout) findViewById(R.id.login_layout_copyright);
        et_user = (EditText) findViewById(R.id.login_et_username);
        et_password = (EditText) findViewById(R.id.login_et_password);
        btn_login = (Button) findViewById(R.id.login_btn_login);
        cb_password = (CheckBox) findViewById(R.id.login_cb_password);
        cb_save = (CheckBox) findViewById(R.id.login_cb_save);

        btn_login.setOnClickListener(this);


        //底部长按设置IP地址事件
        layout_bottom.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showSettingIPDialog();
                return true;
            }

        });

        int saveType = mPreferences.getInt("saveusers",2);
        //记住密码的判断  saveType 为2 表示不记住密码,1表示记住密码
        if(saveType == 2){
            cb_save.setChecked(false);
        }else if(saveType == 1){
            password = mPreferences.getString("userpasswors", "");
            cb_save.setChecked(true);
            et_password.setText(password);
        }
        et_user.setText(userName);

        //密码显示和隐藏监听
        cb_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //如果选中，显示密码
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                if(!TextUtils.isEmpty(et_password.getText().toString())){
                    et_password.setSelection(et_password.getText().toString().length());
                }
            }
        });
    }

    //设置IP地址弹出框
    private void showSettingIPDialog() {
        ipDialog = new AlertDialog.Builder(this).create();
        Window window = ipDialog.getWindow();
        ipDialog.show();
        window.setContentView(R.layout.dialog_setting_ip);
        //软键盘弹出
        ipDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        ipDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.ALPHA_CHANGED);
        RelativeLayout layout = (RelativeLayout) ipDialog.findViewById(R.id.settingIP_layout);
        ipDialog.getWindow().setLayout(layout.getLayoutParams().width+100, layout.getLayoutParams().width+100);

        dialog_et_ip = (EditText) ipDialog.findViewById(R.id.settingIP_et_ip);
        dialog_et_port= (EditText) ipDialog.findViewById(R.id.settingIP_et_port);
        dialog_et_name = (EditText) ipDialog.findViewById(R.id.settingIP_et_name);
        dialog_iv_close = (ImageView) ipDialog.findViewById(R.id.settingIP_iv_close);
        dialog_btn_sumbit = (Button) ipDialog.findViewById(R.id.settingIP_btn_submit);

        dialog_iv_close.setOnClickListener(this);
        dialog_btn_sumbit.setOnClickListener(this);

        SharedPreferences ipSharedPreferences = getSharedPreferences("IP",MODE_PRIVATE);
        String ip = ipSharedPreferences.getString("ipad", "");
        String port = ipSharedPreferences.getString("port", "");
        String name = ipSharedPreferences.getString("et_name", "");
        if(!ip.equals("")&&(!port.equals(""))&&(!name.equals(""))){
            dialog_et_ip.setText(ip);
            dialog_et_port.setText(port);
            dialog_et_name.setText(name);
        }else{
            dialog_et_ip.setText(Constants.login_ip);
            dialog_et_port.setText(Constants.login_port);
            dialog_et_name.setText(Constants.login_name);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_login://登录按钮点击事件
                userName = et_user.getText().toString().trim();
                password = et_password.getText().toString().trim();
                if(userName==null||userName.length()==0){
                    toastLong("用户名为空，请输入用户名！");
                }else if(password==null||password.length()==0){
                    toastLong("密码为空，请输入正确的密码！");
                }else {
                    md5Password = Tools.md5(password);
                    login(userName,password,md5Password,1);
                }
                break;

            case R.id.settingIP_iv_close://设置IP地址弹出框关闭按钮
                ipDialog.dismiss();
                Tools.backgroundAlphas(LoginActivity.this,1f);
                break;

            case R.id.settingIP_btn_submit://设置IP地址弹出框确定提交按钮
                SharedPreferences ipSharedPreferences = getSharedPreferences("IP",MODE_PRIVATE);
                SharedPreferences.Editor ipEditor = ipSharedPreferences.edit();
                ipEditor.putString("ipaddress","http://" + dialog_et_ip.getText().toString() + ":" + dialog_et_port.getText().toString() + "/" + dialog_et_name.getText().toString());
                ipEditor.putString("ipad", dialog_et_ip.getText().toString() );
                ipEditor.putString("port", dialog_et_port.getText().toString() );
                ipEditor.putString("et_name", dialog_et_name.getText().toString() );
                ipEditor.commit();
                ipDialog.dismiss();
                Tools.backgroundAlphas(LoginActivity.this,1f);
                break;
        }

    }

    /**
     * 账号密码登录方法
     * @param userName 用户名
     * @param password 用户密码
     * @param md5Password MD5加密后的密码
     * @param type 登录方法类型
     */
    private void login(String userName, String password, String md5Password, int type) {
        try {
            LoginThread1 thread = new LoginThread1(userName,md5Password);
            thread.start();
            thread.join();
            if(loginContents!=null&&loginContents.length()!=0){
                JSONObject object = new JSONObject(loginContents);
                String errorCode = object.getString("errcode");//为0 表示用户名密码正确， 为1表示密码错误或者用户名不存在
                String errorMsg = object.getString("errmsg");
                if(errorCode.equals("0")){
                    JSONObject date = object.getJSONObject("data");

                    JSONObject userInfo = date.getJSONObject("userinfo");
                    personalInformationList = new ArrayList<PersonalInformationEntity>();
                    personalInformationEntity = new PersonalInformationEntity();
                    personalInformationEntity.setID(userInfo.getString("ID"));
                    personalInformationEntity.setNAME(userInfo.getString("NAME"));
                    personalInformationEntity.setDISPNAME(userInfo.getString("DISPNAME"));
                    personalInformationEntity.setTEL(userInfo.getString("TEL"));
                    personalInformationEntity.setDEPID(userInfo.getString("DEPID"));
                    personalInformationEntity.setDEPNAME(userInfo.getString("DEPNAME"));
                    Constants.PERSONLIABLE = userInfo.getString("PERSONLIABLE");
                    Constants.RESPONSIBLEMAN = userInfo.getString("RESPONSIBLEMAN");
                    Constants.USERID = userInfo.getString("ID");
                    SharedPreferencesUtil.putValue(LoginActivity.this, "ID", userInfo.getString("ID"));
                    personalInformationList.add(personalInformationEntity);
                    basicDateEntity.setPersonalInformationList(personalInformationList);

                    JSONObject config = date.getJSONObject("config");


                    SharedPreferencesUtil.putValue(LoginActivity.this, "imgReadURL", config.getString("imgReadURL"));
                    SharedPreferencesUtil.putValue(LoginActivity.this, "videoReadURL", config.getString("videoReadURL"));
                    SharedPreferencesUtil.putValue(LoginActivity.this, "headImgThnURL", config.getString("headImgThnURL"));
                    SharedPreferencesUtil.putValue(LoginActivity.this, "headImgURL", config.getString("headImgURL"));
                    String headImgURL = config.getString("headImgURL");
                    SharedPreferencesUtil.putValue(LoginActivity.this, "iconPath", Tools.getSDPath(Constants.ImageCameraPath)+headImgURL.split("/")[headImgURL.split("/").length-1]);//头像本地路径

                    mEditor = mPreferences.edit();
                    mEditor.putString("videoWriteURL", config.getString("videoWriteURL"));
                    mEditor.putString("imgDelURL", config.getString("imgDelURL"));
                    mEditor.putString("imgWriteURL", config.getString("imgWriteURL"));
                    mEditor.putString("videoDelURL", config.getString("videoDelURL"));

                    if (cb_save.isChecked()) {
                        mEditor.putInt("saveusers", 1);
                        mEditor.putString("userpws", md5Password);
                        mEditor.putBoolean("result",true);
                    }else{
                        mEditor.putInt("saveusers", 2);
                        mEditor.putBoolean("result",false);
                    }

                    if(!password.equals("")){
                        mEditor.putString("userpasswors", password);
                    }
                    mEditor.putString("userid", userName);
                    mEditor.commit();
                    SharedPreferencesUtil.putValue(LoginActivity.this, "md5", md5Password);

                    Countents countents = new Countents(LoginActivity.this);
                    countents.SQLitedate_gestrueDate();
                    List<UserConfigureEntity> configureList = countents.getSqlUserConfigureDate();

                    //手势密码不为空
                    if (basicDateEntity.getGestruelists().size() != 0) {
                        for (int i = 0; i < basicDateEntity.getGestruelists().size(); i++) {
                            if (basicDateEntity.getGestruelists().get(i).getUserid().equals(basicDateEntity.getPersonalInformationList().get(0).getID())) {
                                userId = basicDateEntity.getGestruelists().get(i).getUserid();
                                gesPassword = basicDateEntity.getGestruelists().get(i).getGestruepassword();
                                log("登录界面","password=="+password);
                                log("登录界面","userId=="+userId);
                                log("登录界面","password=="+basicDateEntity.getGestruelists().get(i).getUserpassword());
                                if(password!= "" && !basicDateEntity.getGestruelists().get(i).getUserpassword().equals(password)){
                                    Countents.updatePwd(password, userId, LoginActivity.this);
                                }
                            }
                        }
                        if (type ==1){
                            for (int i = 0; i < configureList.size(); i++) {
                                if(cb_save.isChecked()&&configureList.get(i).getUserId().equals(Constants.USERID) || (userId !=null && userId.equals(basicDateEntity.getPersonalInformationList().get(0).getID()))){
                                    Intent intent = new Intent(LoginActivity.this,FragmentMainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    return;
                                }
                            }
                            Intent intent = new Intent(LoginActivity.this,GestureEditActivity.class);
                            intent.putExtra("first", "1");
                            startActivity(intent);
                            finish();
                        }

                    }else{//用户手势密码为空
                        int first = mPreferences.getInt(userId,0);
                        //初次进入设置手势密码或不保存密码要设置手势密码
                        if(first == 0||(!cb_save.isChecked())){
                            Intent intent = new Intent(LoginActivity.this,GestureEditActivity.class);
                            intent.putExtra("first", "1");
                            mPreferences.edit().putInt(userId,1).commit();
                            startActivity(intent);
                            finish();
                        }else{
                            Intent intent = new Intent(LoginActivity.this,FragmentMainActivity.class);
                            Log.e("登录界面","界面跳转4");
                            startActivity(intent);
                            finish();
                            return;
                        }
                    }



                }else if(errorCode.equals("1")){

                }else {

                }

            }else{//没有连接到服务器获取到登录返回数据
                handler.sendEmptyMessage(107);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    class LoginThread1 extends Thread{
        private String name;//用户名
        private String md5Pwd;//MD5加密后的密码

        public LoginThread1(String name,String md5Pwd){
            this.name = name;
            this.md5Pwd = md5Pwd;
        }

        @Override
        public void run() {
            log("登录界面","启动登录线程");
            try {
                ACTION_IP = getSharedPreferences("IP", MODE_PRIVATE).getString("ipaddress", "");
                if(ACTION_IP.equals("")){//如果为空，让其直接从Constants里读配置
                    mPreferences = getSharedPreferences("IP", MODE_PRIVATE);
                    mEditor = mPreferences.edit();
                    mEditor.putString("ipaddress","http://" + Constants.login_ip+ ":" + Constants.login_port + "/" + Constants.login_name);
                    mEditor.putString("ipad", Constants.login_ip );
                    mEditor.putString("port", Constants.login_port);
                    mEditor.putString("et_name", Constants.login_name );
                    mEditor.commit();
                    ACTION_IP = getSharedPreferences("IP", MODE_PRIVATE).getString("ipaddress", "");
                }
                URL url = new URL(ACTION_IP+Constants.LOGIN + "?"+"userId=" + name + "&" + "password=" + md5Pwd);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(3000);
                conn.setReadTimeout(3000);
                conn.connect();

                //设置cookie
                String cookie = conn.getHeaderField("set-cookie");
                cookie +=  ";domain="+ACTION_IP.split(":")[1].substring(2);
                mPreferences.edit().putString("ticket", cookie).commit();

                InputStream is = conn.getInputStream();
                InputStreamReader isr =  new InputStreamReader(is,"UTF-8");
                BufferedReader bf = new BufferedReader(isr);
                StringBuffer res = new StringBuffer();
                String line = null;
                while ((line = bf.readLine()) != null) {
                    res.append(line.trim().replaceAll("\t", ""));
                }
                bf.close();
                isr.close();
                is.close();
                loginContents = res.toString();
            } catch (IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(107);
            }


        }
    }
}
