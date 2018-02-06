package com.csc.mobile.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.csc.mobile.R;
import com.csc.mobile.base.BaseActivity;
import com.csc.mobile.db.Countents;
import com.csc.mobile.db.DatabaseHelper;
import com.csc.mobile.entity.BasicDateEntity;
import com.csc.mobile.utils.SharedPreferencesUtil;
import com.csc.mobile.view.GestureContentView;
import com.csc.mobile.view.GestureDrawline;
import com.csc.mobile.view.LockIndicator;

/**
 * 手势密码设置界面
 * Created by 随风 on 2018/1/31.
 */

public class GestureEditActivity extends BaseActivity {
    private String password;//用户密码
    private String firstFlag = null;//第一次进入设置的标识
    private boolean mIsFirstInput = true;
    private String mFirstPassword = null;
    private int flag = 0;

    private LockIndicator mLockIndicator;
    private FrameLayout fl_container;
    private RelativeLayout rl_skip;
    private TextView tv_reset,tv_tip;

    private static SharedPreferences mPreferences;
    private GestureContentView mGestureContentView;
    private DatabaseHelper helper;
    private BasicDateEntity basicDateEntity = BasicDateEntity.getSingle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        mPreferences = getSharedPreferences("login", MODE_PRIVATE);
        password = mPreferences.getString("userpasswors", "");
        firstFlag = getIntent().getStringExtra("first");
        initViews();
        setUpListeners();
    }

    private void initViews() {
        mLockIndicator = (LockIndicator) findViewById(R.id.gesture_lock_indicator);
        fl_container = (FrameLayout) findViewById(R.id.gesture_container);
        rl_skip = (RelativeLayout) findViewById(R.id.gesture_rl_skip);
        tv_reset = (TextView) findViewById(R.id.gesture_tv_reset);
        tv_tip = (TextView) findViewById(R.id.gesture_tv_tip);
        tv_reset.setClickable(false);

        if(firstFlag !=null&&firstFlag.equals("1")){
            rl_skip.setVisibility(View.VISIBLE);
            flag = 1;
        }
        rl_skip.setOnClickListener(new MyClick());

        //初始化一个显示各个点的viewGroup
        initGestureContentView();

    }

    private void initGestureContentView() {
        mGestureContentView = new GestureContentView(this, false, "", new GestureDrawline.GestureCallBack() {
            @Override
            public void onGestureCodeInput(String inputCode) {
                if(!isInputPassValidate(inputCode)){
                    tv_tip.setText(Html.fromHtml("<font color='#c70c1e'>最少链接4个点, 请重新输入</font>"));
                    mGestureContentView.clearDrawlineState(0L);
                    return;
                }

                if(mIsFirstInput){
                    mFirstPassword = inputCode;
                    updateCodeList(inputCode);
                    mGestureContentView.clearDrawlineState(0L);
                    tv_reset.setClickable(true);
                    tv_reset.setText("重新设置手势密码");
                }else {
                    if (inputCode.equals(mFirstPassword)) {
                        //将手势密码保存到本地
                        SharedPreferencesUtil.putValue(GestureEditActivity.this, "gesturepassword", mFirstPassword);
                        if (firstFlag != null && firstFlag.equals("1")) {
                            helper = new DatabaseHelper(GestureEditActivity.this);
                            SQLiteDatabase db = helper.getReadableDatabase();
                            ContentValues valuse = new ContentValues();
                            valuse.put("userId", basicDateEntity.getPersonalInformationList().get(0).getID());
                            valuse.put("gesturepassword", mFirstPassword);
                            valuse.put("username", basicDateEntity.getPersonalInformationList().get(0).getNAME());
                            valuse.put("userpasword", password);
                            db.insert(DatabaseHelper.NAME_GESTRUE_TAL, null, valuse);
                            db.close();
                        } else {
                            Countents countents = new Countents(GestureEditActivity.this);
                            countents.SQLitedate_gestrueDate();
                            for (int i = 0; i < basicDateEntity.getGestruelists().size(); i++) {
                                if (basicDateEntity.getGestruelists().get(i).getUserid().equals(basicDateEntity.getPersonalInformationList().get(0).getID())) {
                                    ContentValues values = new ContentValues();
                                    values.put("gesturepassword", mFirstPassword);
                                    DatabaseHelper dbhelper = new DatabaseHelper(GestureEditActivity.this);
                                    SQLiteDatabase db = dbhelper.getWritableDatabase();
                                    db.update(DatabaseHelper.NAME_GESTRUE_TAL, values, "userId=?", new String[]{basicDateEntity.getPersonalInformationList().get(0).getID()});
                                    db.close();
                                }
                            }
                        }

                        Toast.makeText(GestureEditActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        mGestureContentView.clearDrawlineState(0L);
                        if (flag == 1) {
                            GestureEditActivity.this.finish();
                            Intent intent = new Intent(GestureEditActivity.this, FragmentMainActivity.class);
                            startActivity(intent);
                        } else {
                            GestureEditActivity.this.finish();
                        }
                    }else {
                        tv_tip.setText(Html.fromHtml("<font color='#c70c1e'>与上一次绘制不一致，请重新绘制</font>"));
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureEditActivity.this, R.anim.shake);
                        tv_tip.startAnimation(shakeAnimation);
                        // 保持绘制的线，1.5秒后清除
                        mGestureContentView.clearDrawlineState(1300L);
                    }
                }
                mIsFirstInput = false;
            }

            @Override
            public void checkedSuccess() {

            }

            @Override
            public void checkedFail(String inputCode) {

            }
        });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(fl_container);
        updateCodeList("");
    }

    private void setUpListeners() {
        tv_reset.setOnClickListener(new MyClick());
    }
    private void updateCodeList(String inputCode) {
        // 更新选择的图案
        mLockIndicator.setPath(inputCode);
    }

    private boolean isInputPassValidate(String inputPassword) {
        if (TextUtils.isEmpty(inputPassword) || inputPassword.length() < 4) {
            return false;
        }
        return true;
    }

    class MyClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.gesture_rl_skip:
                    startActivity(new Intent(GestureEditActivity.this, FragmentMainActivity.class));
                    finish();
                    break;
                case R.id.gesture_tv_reset:
                    mIsFirstInput = true;
                    updateCodeList("");
                    tv_tip.setText("绘制解锁图案");
                default:
                    break;
            }
        }
    }
}
