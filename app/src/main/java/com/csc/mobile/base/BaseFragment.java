package com.csc.mobile.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.csc.mobile.entity.BasicDateEntity;
import com.csc.mobile.interfaces.BackHandledInterface;
import com.csc.mobile.interfaces.MyListener;

/**
 * Created by 随风 on 2018/2/1.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    //protected MyDialog dialog;//页面弹出提示窗口
    protected BackHandledInterface mBackHandledInterface;
    protected MyListener myListener;
    protected Context ct;
    protected String ticket;
    private String ACTION_IP;
    private BasicDateEntity basicDateEntity = BasicDateEntity.getSingle();
    private boolean toOther;
    /**
     * 所有继承BackHandledFragment的子类都将在这个方法中实现物理Back键按下后的逻辑
     * FragmentActivity捕捉到物理返回键点击事件后会首先询问Fragment是否消费该事件
     * 如果没有Fragment消息时FragmentActivity自己才会消费该事件
     */
    public abstract boolean onBackPressed();

    protected MyDialog dialog;//页面弹出提示窗口
    /**
     * Dialog展现Handler
     */
    public Handler _handle = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 107) {
                myListener.toOtherFragment(3,"opCollectCoord");
            }else if(message.what == 106){
                myListener.toOtherFragment(3,"corrigendum");
            }else{
                String msg = message.getData().getString("msg");
                dialog.show(msg);
            }

        };
    };

    /**
     * 隐藏Dialog Runnable
     */
    public Runnable dismissDialogRun = new Runnable() {
        public void run() {
            dialog.dismiss();
        }

    };



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ct = getActivity();
        SharedPreferences sp =  ct.getSharedPreferences("login", ct.MODE_PRIVATE);
        ticket = sp.getString("ticket", "");
        if (dialog == null) {
            dialog = new MyDialog(getActivity());
            dialog.setCancelable(true);
        }
        if (!(getActivity() instanceof BackHandledInterface)) {
            throw new ClassCastException("Hosting Activity must implement BackHandledInterface");
        } else {
            this.mBackHandledInterface = (BackHandledInterface) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myListener = (MyListener) activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        //告诉FragmentActivity，当前Fragment在栈顶
        //mBackHandledInterface.setSelectedFragment(this);
    }

    /**
     * H5调原生
     * @param
     */
    public class JsInterface {

        @JavascriptInterface
        public void call(String type,String json) {
           /* if(type.equals("webview")){
                try {
                    JSONObject object=new JSONObject(json);
                    String url=object.getString("url");
                    myListener.showMessage(Constants.JUMP_OTHER_ACTIVITY,url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            if(type.equals("redirect")||type.equals("forward")){
                try {
                    JSONObject object=new JSONObject(json);
                    String url=object.getString("url");
                    String data = object.getString("urlParams");
                    myListener.showMessage(Constants.JUMP_OTHER_ACTIVITY,url+data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            if(type.equals("serviceAddress")){
                myListener.showMessage(101,  json);
            }
            if (type.equals("data-collection")) {//打开坐标采集功能
                _handle.sendEmptyMessage(107);

            }
            if(type.equals("scan")){
                myListener.showMessage(Constants.JUMP_SCAN_ACTIVITY,json);
            }
            if (type.equals("show-message")) {//打开消息列表
                Intent i = new Intent(getActivity(),MyMassageActivity.class);
                startActivity(i);
//				Toast.makeText(getActivity(), "打开消息列表", Toast.LENGTH_SHORT).show();
//				myListener.showMessage(Constants.JUMP_FirstPageMessage_ACTIVITY,"0");
//				myListener.showMessage(Constants.JUMP_FirstPageMessage_ACTIVITY,"1");
            }
            if(type.equals("lookNotice")){//滚动栏
                //得到消息集合
                Countents countents = new Countents(getActivity());
                countents.SQLitedate_message();
                try {
                    JSONObject object=new JSONObject(json);
                    String id = object.getString("id");
                    String time = object.getString("begintime");
                    String title = object.getString("title");
                    String content = object.getString("text");
                    String picture = object.getString("img");
                    String releaseperson = object.getString("publisher");
                    String url = object.getString("url");
                    if(url.equals("null")){
                        Intent intent = new Intent(getActivity(),MessageDetailsActivity.class);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                        intent.putExtra("time", dateFormat.format(new Date(Long.parseLong(time))));
                        intent.putExtra("title", title);
                        intent.putExtra("content",content);
                        intent.putExtra("picture",picture);
                        intent.putExtra("releaseperson",releaseperson);
                        startActivity(intent);
                    }else{
                        String urls = null;
                        ACTION_IP = getActivity().getSharedPreferences("IP", getActivity().MODE_PRIVATE).getString("ipaddress", "");
                        String ftp = (String) url.subSequence(0, 3);
                        String http = (String) url.subSequence(0, 4);
                        if(ftp.equals("ftp") || http.equals("http")){
                            urls = url;
                        }else{
                            urls = ACTION_IP + url;
                        }
                        //调H5界面
                        Intent intent = new Intent(getActivity(),OtherActivity.class);
                        intent.putExtra("content", urls);
                        startActivityForResult(intent, 1);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if(type.equals("corrigendum")){//资源勘误
                _handle.sendEmptyMessage(106);
            }*/
        }
    }

    /**
     * Toast提示
     * @param msg
     */
    public void alertMsg(String msg) {
        alertHandle.sendMessage(getMessage(msg));
    }
    /**
     * 页面Toast提示Handler
     */
    public Handler alertHandle = new Handler() {
        public void handleMessage(Message message) {
            String msg = message.getData().getString("msg");
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        };
    };

    /**
     * 展现Dialog
     * @param msg
     */
    public void showDialog(String msg) {
        _handle.sendMessage(getMessage(msg));
    }

    /**
     * 隐藏Dialog
     */
    public void dismissDialog() {
        _handle.post(dismissDialogRun);
    }
    /**
     * 生成Message
     * @param msg
     * @return
     */
    public Message getMessage(String msg) {
        Message message = new Message();
        message.what = 1;
        Bundle data = new Bundle();
        data.putString("msg", msg);
        message.setData(data);
        return message;
    }
    @Override
    public void onClick(View v) {

    }
}