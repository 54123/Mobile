package com.csc.mobile.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 随风 on 2018/1/29.
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

    }

    public void toastLong(String msg){
        Toast.makeText(BaseActivity.this,msg,Toast.LENGTH_LONG).show();
    }
    public void toastShort(String msg){
        Toast.makeText(BaseActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    public void log(String tag,String msg){
        if(Constants.debug){
            Log.e(tag,msg);
        }
    }
}
