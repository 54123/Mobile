package com.csc.mobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.csc.mobile.R;
import com.csc.mobile.base.BaseFragment;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by 随风 on 2018/1/31.
 */

public class MineFragment extends BaseFragment {

    private View mView;

    private String ACTION_IP;

    private BitmapUtils bitmapUtils;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mine,null);
        ACTION_IP = getActivity().getSharedPreferences("IP", getActivity().MODE_PRIVATE).getString("ipaddress", "");

        bitmapUtils = new BitmapUtils(getActivity());
        loadData();
        return mView;
    }

    //下载数据
    private void loadData() {

    }


    @Override
    public boolean onBackPressed() {
        return false;
    }
}
