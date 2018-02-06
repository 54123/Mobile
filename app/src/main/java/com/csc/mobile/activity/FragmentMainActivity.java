package com.csc.mobile.activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.csc.mobile.R;
import com.csc.mobile.base.BaseActivity;
import com.csc.mobile.base.BaseFragment;
import com.csc.mobile.base.Constants;
import com.csc.mobile.entity.MainBottomEntity;
import com.csc.mobile.fragment.HomeFragment;
import com.csc.mobile.fragment.MapFragment;
import com.csc.mobile.fragment.MineFragment;
import com.csc.mobile.interfaces.BackHandledInterface;
import com.csc.mobile.interfaces.MyListener;
import com.csc.mobile.utils.JSONConnectUtils;
import com.csc.mobile.utils.SharedPreferencesUtil;
import com.csc.mobile.utils.Tools;
import com.lidroid.xutils.BitmapUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.csc.mobile.utils.Tools.Dp2Px;

/**
 * Created by 随风 on 2018/1/31.
 */

public class FragmentMainActivity extends BaseActivity implements MyListener,BackHandledInterface {

    private LayoutInflater inflater;
    private LinearLayout layout_buttom;//底部标签栏

    private BaseFragment mBackHandedFragment;
    private HomeFragment mTab01;//主页和分类页面
    private MapFragment mTab03;//地图界面
    private MineFragment mTab04;//我的设置界面

    private int defaultColor = Color.rgb(136, 136, 136);//底部标签文字默认颜色
    private int selectColor = Color.rgb(0, 133, 207);//底部标签文字点选颜色
    private static final String TAG = "主界面";
    private String ACTION_IP;
    private String targetUrl;//锚点界面URL（现在加载的界面URL，初始为主页面URL）
    private String tabText="";//底部按钮文字
    private String contents;//底部按钮数字

    private FragmentManager fragmentManager;
    private BitmapUtils bitmapUtils;

    private MainBottomEntity bottomTab;
    private List<MainBottomEntity> bottomTabList = new ArrayList<MainBottomEntity>();
    private LinearLayout layout; //标签控件   图片和文字的LinearLayout
    private RelativeLayout layoutIv;//子控件是两个ImageView，一个是选中的ImageView，一个是默认的ImageView
    private ImageView iv_tab,iv_tabSel;//默认的ImageView,选中的ImageView
    private TextView tv_tab;//文字控件
    private HashMap<Integer, LinearLayout> mapLayout = new HashMap<Integer, LinearLayout>();//标签控件集合
    private HashMap<Integer, TextView> mapTv = new HashMap<Integer, TextView>();//标签文字控件集合
    private HashMap<Integer, ImageView> mapIv = new HashMap<Integer, ImageView>();//标签默认状态的控集合
    private HashMap<Integer, ImageView> mapIvSel = new HashMap<Integer, ImageView>();//标签选中状态的控件集合
    private HashMap<Integer, String> mapUrl = new HashMap<Integer, String>();//默认状态下底部标签图片url
    private HashMap<Integer, String> mapUrlSel = new HashMap<Integer, String>();//选中状态下底部标签图片url

    private  List<HomeFragment> webviewList = new ArrayList<HomeFragment>();//主页和分类页面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ACTION_IP = getSharedPreferences("IP",MODE_PRIVATE).getString("ipaddress", "");
        fragmentManager = getFragmentManager();
        inflater = LayoutInflater.from(this);
        layout_buttom = (LinearLayout) findViewById(R.id.main_layout_bottom);
        bitmapUtils = new BitmapUtils(FragmentMainActivity.this);
        loadDate();//加载底部数据
    }

    //加载初始化底部按钮数据
    private void loadDate() {
        contents = getSharedPreferences("主页数据", Context.MODE_PRIVATE).getString("result", "");
        int times = getSharedPreferences("主页数据", Context.MODE_PRIVATE).getInt("time", -1);//数据使用次数，使用100次后重新加载数据

        if(contents!=""&&contents.length()!=0&&times<100&&times>0){
            getSharedPreferences("主页数据", Context.MODE_PRIVATE).edit().putInt("time", times+1).commit();//数据使用次数加1
            analysisDate(contents);
        }else{
            BottomTabThread thread = new BottomTabThread();
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(contents!=null&&contents.length()!=0){
                getSharedPreferences("主页数据", Context.MODE_PRIVATE).edit().putString("result", contents).commit();
                getSharedPreferences("主页数据", Context.MODE_PRIVATE).edit().putInt("time", 0).commit();
                analysisDate(contents);
            }else{
                Toast.makeText(FragmentMainActivity.this, "连接服务器失败，请检查网络后重试", Toast.LENGTH_SHORT).show();
            }
        };
    }

    /**
     * 解析下载的json数据，设置底部按钮
     */
    public void analysisDate(String result){
        bottomTabList= JSONConnectUtils.getMainFragment(result);
        targetUrl = bottomTabList.get(0).getFLDTARGET();
        String type = bottomTabList.get(0).getFLDTYPE();
        String target = bottomTabList.get(0).getFLDTARGET();

        if(type.equals("1")){
            tabText = bottomTabList.get(0).getFLDTEXT();
            showMessage(0, "");
        }else if(type.equals("2")){
            tabText = "";
            if(target.equals("map")){
                showMessage(2, "");
            }else if("mine".equals(target)){
                showMessage(3, "");
            }
        }
        initDownButton(0);
    }

    /**
     * 初始化底部按钮
     * @param index 0--初始化时传的值  1--从地图界面返回时传的值
     */
    private void initDownButton(int index) {
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        for (int i = 0; i < bottomTabList.size(); i++) {
            bottomTab = bottomTabList.get(i);
            //底部图片和文字的linearlayout
            layout = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(Dp2Px(getApplicationContext(), width/bottomTabList.size()), Dp2Px(getApplicationContext(), 48),1.0f);
            layout.setDescendantFocusability(LinearLayout.FOCUS_BEFORE_DESCENDANTS);
            layout.setGravity(Gravity.CENTER);
            layout.setLayoutParams(lp);
            layout.setTag(bottomTab.getFLDTEXT()+"~"+bottomTab.getFLDTYPE()+"~"+bottomTab.getFLDTARGET());
            layout.setBackgroundResource(R.color.navigation_bar);
            //每个item的点击事件
            layout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String  tag = (String) v.getTag();
                    String[] strArray = tag.split("~");
                    String type = strArray[1];
                    String target = strArray[2];

                    if(type.equals("1")){
                        targetUrl = target;
                        tabText = strArray[0];
                        showMessage(0,"");
                    }else if(type.equals("2")){
                        tabText = "";
                        if(target.equals("map")){
                            showMessage(2, "");
                        }else if("mine".equals(target)){
                            showMessage(3, "");
                        }
                    }

                    layout.setSelected(true);
                    for (int j = 0; j < mapLayout.size(); j++) {
                        if(mapLayout.get(j).getTag().equals(tag)){
                            mapLayout.get(j).setSelected(true);
                        }
                    }

                    for (int j = 0; j < mapIv.size(); j++) {
                        if(mapIv.get(j).getTag().equals(tag)){
                            mapIv.get(j).setVisibility(View.GONE);
                            mapIvSel.get(j).setVisibility(View.VISIBLE);
                        }
                    }
                    for (int j = 0; j < mapTv.size(); j++) {
                        if(mapTv.get(j).getTag().equals(tag)){
                            mapTv.get(j).setTextColor(selectColor);
                            return;
                        }
                    }
                }
            });

            //判断图片地址开头是不是"http"、"ftp"
            String ftp = (String) bottomTab.getFLDICON().subSequence(0, 3);
            String http = (String) bottomTab.getFLDICON().subSequence(0, 4);
            String ftpSel = (String) bottomTab.getFLDICONSEL().subSequence(0, 3);
            String httpSel = (String) bottomTab.getFLDICONSEL().subSequence(0, 4);

            if(ftp.equals("ftp") || http.equals("http")){
                mapUrlSel.put(i, bottomTab.getFLDICON());
            }else{
                mapUrlSel.put(i,ACTION_IP+ bottomTab.getFLDICON());
            }
            if(ftpSel.equals("ftp") || httpSel.equals("http")){
                mapUrl.put(i, bottomTab.getFLDICONSEL());
            }else{
                mapUrl.put(i,ACTION_IP+ bottomTab.getFLDICONSEL());
            }

            //初始化RelativeLayout
            layoutIv = new RelativeLayout(getApplicationContext());
            RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutIv.setLayoutParams(lp3);
            layoutIv.setTag(bottomTab.getFLDTYPE()+bottomTab.getFLDTARGET());

            //初始化默认的ImageView
            iv_tab = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(Tools.Dp2Px(getApplicationContext(), 20), Tools.Dp2Px(getApplicationContext(), 20));
            iv_tab.setLayoutParams(lp2);
            iv_tab.setScaleType(ImageView.ScaleType.FIT_XY);
            iv_tab.setTag(bottomTab.getFLDTEXT()+"~"+bottomTab.getFLDTYPE()+"~"+bottomTab.getFLDTARGET());
            bitmapUtils.display(iv_tab,mapUrlSel.get(i));
            mapIv.put(i, iv_tab);
            layoutIv.addView(iv_tab);

            //初始化选中的ImageView，
            iv_tabSel = new ImageView(getApplicationContext());
            iv_tabSel.setLayoutParams(lp2);
            iv_tabSel.setScaleType(ImageView.ScaleType.FIT_XY);
            iv_tabSel.setTag(bottomTab.getFLDTEXT()+"~"+bottomTab.getFLDTYPE()+"~"+bottomTab.getFLDTARGET());
            iv_tabSel.setVisibility(View.GONE);
            bitmapUtils.display(iv_tabSel,mapUrlSel.get(i));
            mapIvSel.put(i, iv_tabSel);
            layoutIv.addView(iv_tabSel);

            //初始化TextView
            tv_tab = new TextView(getApplicationContext());
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv_tab.setText(bottomTab.getFLDTEXT());
            tv_tab.setTag(bottomTab.getFLDTEXT()+"~"+bottomTab.getFLDTYPE()+"~"+bottomTab.getFLDTARGET());
            tv_tab.setLayoutParams(lp1);
            tv_tab.setTextSize(12);

            mapLayout.put(i, layout);

            if((index!=1 && i==0) || (index==1 && bottomTab.getFLDTARGET().equals("map"))){
                mapLayout.get(i).setSelected(true);
                mapIv.get(i).setVisibility(View.GONE);
                mapIvSel.get(i).setVisibility(View.VISIBLE);
                tv_tab.setTextColor(selectColor);
            }else{
                iv_tab=mapIv.get(i);
                tv_tab.setTextColor(defaultColor);
            }

            layout.addView(layoutIv);
            layout.addView(tv_tab);
            mapTv.put(i, tv_tab);
            if(index!=2)
                layout_buttom.addView(layout);
        }
    }

    @Override
    public void showMessage(int index, String text) {

        if (index < 4) {
            resetBtn();
            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            hideFragments(transaction);
            Bundle data = new Bundle();
            data.putString("TEXT", text);
            switch (index) {
                case 0:
                    boolean is = true;
                    if(webviewList!=null && webviewList.size()!=0) {
                        for (int i = 0; i < webviewList.size(); i++) {
                            if(webviewList.get(i).type.equals(targetUrl)){
                                if(tabText.equals("分类") && Constants.refreshCategory){
                                    webviewList.remove(i);
                                    Constants.refreshCategory = false;
                                    is=true;
                                }else{
                                    webviewList.get(i).getArguments().putString("TEXT", text);
                                    transaction.show(webviewList.get(i));
                                    mTab01=webviewList.get(i);
                                    is=false;
                                }
                            }
                        }
                        if(is){
                            Constants.targetUrl = targetUrl;
                            mTab01 = new HomeFragment();
                            mTab01.setArguments(data);
                            transaction.add(R.id.main_layout_content, mTab01);
                            webviewList.add(mTab01);
                        }
                    }else{
                        Constants.targetUrl = targetUrl;
                        mTab01 = new HomeFragment();
                        mTab01.setArguments(data);
                        transaction.add(R.id.main_layout_content, mTab01);
                        webviewList.add(mTab01);
                    }
                    setSelectedFragment(mTab01);
                    break;
                case 2:
                    if (mTab03 == null) {
                        mTab03 = new MapFragment();
                        mTab03.setArguments(data);
                        transaction.add(R.id.main_layout_content, mTab03);
                    } else{
                        mTab03.getArguments().putString("TEXT", text);
                        transaction.show(mTab03);
                        //判断是否有删除资源操作
                        if(Constants.isDeleteResurce){
                            if(mTab03 != null && mTab03.qGraphicsLayer.getGraphicIDs() != null && mTab03.qGraphicsLayer.getGraphicIDs().length > 0){
                                mTab03.deleteResurce(Constants.id,Constants.tag);
                            }
                            Constants.isDeleteResurce = false;
                        }
                    }
                    layout_buttom.removeAllViews();
                    setSelectedFragment(mTab03);
                    if(text == ""){
                        initDownButton(1);
                        SharedPreferencesUtil.putValue(FragmentMainActivity.this, "mapLayout", false);
                    }/*else if(text.equals("资源勘误")){
                        RelativeLayout layout = (RelativeLayout) inflater.inflate(
                                R.layout.view_map_bottom, null)
                                .findViewById(R.id.map_bottom);
                        lin.addView(layout);
                        initMapBottom(layout,true);
                        SharedPreferencesUtil.putValue(FragmentMainActivity.this, "mapLayout", true);
                    }*/
                    break;
                case 3:
                    if (mTab04 == null) {
                        mTab04 = new MineFragment();
                        mTab04.setArguments(data);
                        transaction.add(R.id.main_layout_content, mTab04);
                    } else {
                        mTab04.getArguments().putString("TEXT", text);
                        transaction.show(mTab04);
                    }
                    setSelectedFragment(mTab04);
                    break;
            }
            transaction.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }


    }
    @Override
    public void toOtherFragment(int fragmentNum, String motherName) {

    }

    @Override
    public void setSelectedFragment(BaseFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }


    /**
     * 清除掉所有的选中状态。
     */
    private void resetBtn() {
        if(mapLayout!=null&&mapLayout.get(0)!=null){
            for (int j = 0; j < mapLayout.size(); j++) {
                mapLayout.get(j).setSelected(false);
            }
            for (int j = 0; j < mapIv.size(); j++) {
                mapIv.get(j).setVisibility(View.VISIBLE);
                mapIvSel.get(j).setVisibility(View.GONE);
            }
            for (int j = 0; j < mapTv.size(); j++) {
                mapTv.get(j).setTextColor(defaultColor);
            }
        }
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    @SuppressLint("NewApi")
    private void hideFragments(FragmentTransaction transaction) {
        if (mTab01 != null) {
            transaction.hide(mTab01);
        }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
        if (mTab04 != null) {
            transaction.hide(mTab04);
        }

    }


    //加载底部数据的线程
    class BottomTabThread extends Thread {
        public void run() {
            try {
                ACTION_IP = getSharedPreferences("IP", MODE_PRIVATE).getString("ipaddress", "");

                URL url = new URL(ACTION_IP + Constants.LOAD_MAIN_FRAGMENT_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(3000);
                conn.setReadTimeout(3000);
                conn.connect();
                InputStream inputStream = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer res = new StringBuffer();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    res.append(line.trim().replaceAll("\t", ""));
                }
                inputStreamReader.close();
                inputStream.close();
                bufferedReader.close();
                contents = res.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
