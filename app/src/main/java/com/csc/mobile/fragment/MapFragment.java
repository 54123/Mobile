package com.csc.mobile.fragment;

import com.csc.mobile.base.BaseFragment;
import com.esri.android.map.GraphicsLayer;

/**
 * Created by 随风 on 2018/1/31.
 */

public class MapFragment extends BaseFragment{

    public GraphicsLayer qGraphicsLayer;//查询结果显示图层


    /**
     * 删除资源
     * */
    public void deleteResurce(String id, String tag){

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
