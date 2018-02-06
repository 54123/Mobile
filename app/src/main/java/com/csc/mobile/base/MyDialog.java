package com.csc.mobile.base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csc.mobile.R;

/**
 * 自定义等待对话框
 * Created by 随风 on 2018/2/1.
 */

public class MyDialog extends Dialog{

    private LinearLayout layout;
    private TextView tv_tip;
    private ImageView iv_img;

    private boolean flag=true;  //flag为flase，dialog dismiss时不初始化

    public MyDialog(Context context){
        super(context, R.style.dialog);
        init(context);
    }

    public MyDialog(Context context,boolean flag) {
        super(context, R.style.dialog);
        this.flag=flag;
        init(context);
    }

    @SuppressLint("InflateParams")
    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_wait,null);
        layout = (LinearLayout) view.findViewById(R.id.dialog_wait_layout);
        iv_img = (ImageView) view.findViewById(R.id.dialog_wait_img);
        tv_tip = (TextView) view.findViewById(R.id.dialog_wait_tv_tip);

        //加载动画
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.anim.dialog_wait_animation);
        iv_img.startAnimation(hyperspaceJumpAnimation);

        this.setContentView(layout,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        this.setCancelable(false);
    }

    public void setMsg(String msg) {
        tv_tip.setText(msg);
    }

    public void show(String msg){
        setMsg(msg);
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(flag)
            init(this.getContext());
    }
}
