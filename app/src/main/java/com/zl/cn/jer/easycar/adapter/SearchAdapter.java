package com.zl.cn.jer.easycar.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.navisdk.adapter.BNOuterLogUtil;
import com.baidu.navisdk.adapter.BNOuterTTSPlayerCallback;
import com.baidu.navisdk.adapter.BNRoutePlanNode;
import com.baidu.navisdk.adapter.BNaviSettingManager;
import com.baidu.navisdk.adapter.BaiduNaviManager;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.BNDemoGuideActivity;
import com.zl.cn.jer.easycar.activity.myyikai.SearchActivity;
import com.zl.cn.jer.easycar.bean.CityFindList;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.baidu.navisdk.adapter.BNRoutePlanNode.CoordinateType.WGS84;
import static com.zl.cn.jer.easycar.activity.MainActivity.ROUTE_PLAN_NODE;
import static com.zl.cn.jer.easycar.activity.MainActivity.activityList;

/**
 * Created by wanghongra on 2016/9/7.
 */

public class SearchAdapter extends android.widget.BaseAdapter {
    private final List<CityFindList.ListBean> list;
    private final Context context;
    private final String str;
    private static final String APP_FOLDER_NAME = "BNSDKSimpleDemo";
    private final double lon;
    private final double lat;
    private String mSDCardPath = null;
    int index;
    int leng;
    int type;
    private double latend;
    private double lngend;

    public SearchAdapter(Context context, List<CityFindList.ListBean> list,String str,double lat,double lon) {
    this.context=context;
        this.list=list;
        this.str=str;
        this.lon=lon;
        this.lat=lat;
        BNOuterLogUtil.setLogSwitcher(true);
        //初始化导航
        if (initDirs()) {
            initNavi();
        
        }
    }




    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = View.inflate(context, R.layout.searchitem, null);
        TextView address= (TextView) view.findViewById(R.id.search_address_name);
        TextView  kezu = (TextView) view.findViewById(R.id.search_kezu);
        TextView  km = (TextView) view.findViewById(R.id.search_km);
        TextView  juti = (TextView) view.findViewById(R.id.search_address_juti);
        TextView  start = (TextView) view.findViewById(R.id.start_start);
        TextView  stop= (TextView) view.findViewById(R.id.search_stop);

        Button daohang= (Button) view.findViewById(R.id.daohang);


        daohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// 打开log开关
                Log.d("Tag",lat+"----"+lon+"====="+latend+"77777"+lngend);
                routeplanToNavi(lon,lat,lngend,latend,WGS84);

              //  Log.d("Tag",lat+"----"+lon+"====="+latend+"77777"+lngend);
            }
        });



        DecimalFormat df = new DecimalFormat("###.0");

        address.setText(list.get(i).getSite_name());
        kezu.setText(list.get(i).getUseable()+"辆可租");
        km.setText(df.format(list.get(i).getDistance()/1000)+"km");
        if (list.get(i).getAddress().length()>16){
            juti.setText(list.get(i).getAddress().replace(list.get(i).getAddress().substring(16),"..."));
        }else {
            juti.setText(list.get(i).getAddress());
        }

        start.setText(list.get(i).getStartTimeForWork());
        stop.setText("——"+list.get(i).getEndTimeForWork());
        latend = list.get(i).getLat();

        lngend = list.get(i).getLng();


        SpannableStringBuilder builder = new SpannableStringBuilder(list.get(i).getSite_name());
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);


        if (str != null) {
            index = list.get(i).getSite_name().indexOf(str);
            if (index == -1) {
                index = 0;
            }
            leng = str.length();
        }
        builder.setSpan(blueSpan, index, (index + leng), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        address.setText(builder);
        return view;
    }




    private boolean initDirs() {
        String mSDCardPath = getSdcardDir();
        if (mSDCardPath == null) {
            return false;
        }
        File f = new File(mSDCardPath, APP_FOLDER_NAME);
        if (!f.exists()) {
            try {
                f.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    String authinfo = null;

    /**
     * 内部TTS播报状态回传handler
     */
    private Handler ttsHandler = new Handler() {
        public void handleMessage(Message msg) {
            int type = msg.what;
            switch (type) {
                case BaiduNaviManager.TTSPlayMsgType.PLAY_START_MSG: {
                    showToastMsg("Handler : TTS play start");
                    break;
                }
                case BaiduNaviManager.TTSPlayMsgType.PLAY_END_MSG: {
                    showToastMsg("Handler : TTS play end");
                    break;
                }
                default :
                    break;
            }
        }
    };


    /**
     * 内部TTS播报状态回调接口
     */
    private BaiduNaviManager.TTSPlayStateListener ttsPlayStateListener = new BaiduNaviManager.TTSPlayStateListener() {

        @Override
        public void playEnd() {
            showToastMsg("TTSPlayStateListener : TTS play end");
        }

        @Override
        public void playStart() {
            showToastMsg("TTSPlayStateListener : TTS play start");
        }
    };
    public void showToastMsg(final String msg) {

    }

    private void initNavi() {

      //  BNOuterTTSPlayerCallback ttsCallback = null;

        BaiduNaviManager.getInstance().init((SearchActivity)context, mSDCardPath, APP_FOLDER_NAME, new BaiduNaviManager.NaviInitListener() {
            public void onAuthResult(int status, String msg) {
                if (0 == status) {
                    authinfo = "key校验成功!";
                } else {
                    authinfo = "key校验失败, " + msg;
                }

            }

            public void initSuccess() {
                Toast.makeText(context, "百度导航引擎初始化成功", Toast.LENGTH_SHORT).show();
                initSetting();
            }

            public void initStart() {
                Toast.makeText(context, "百度导航引擎初始化开始", Toast.LENGTH_SHORT).show();
            }

            public void initFailed() {
                Toast.makeText(context, "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();
            }


        },  null, ttsHandler, ttsPlayStateListener);

    }

    private String getSdcardDir() {
        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    private void routeplanToNavi(double slat,double slon ,double latEnd,double longEnd,BNRoutePlanNode.CoordinateType coType) {
        BNRoutePlanNode sNode = null;
        BNRoutePlanNode eNode = null;
        sNode = new BNRoutePlanNode(slat,slon, "当面位置", null, coType);
        eNode = new BNRoutePlanNode(latEnd,longEnd, "目的地", null, coType);
        Log.d("enode","=============================================================");

        if (sNode != null && eNode != null) {
            List<BNRoutePlanNode> list = new ArrayList<BNRoutePlanNode>();
            list.add(sNode);
            list.add(eNode);
            BaiduNaviManager.getInstance().launchNavigator((SearchActivity)context, list, 1, true, new DemoRoutePlanListener(sNode));
            Log.d("***********","=============================================================");
        }
    }

    public class DemoRoutePlanListener implements BaiduNaviManager.RoutePlanListener {

        private BNRoutePlanNode mBNRoutePlanNode = null;

        public DemoRoutePlanListener(BNRoutePlanNode node) {
            mBNRoutePlanNode = node;
        }

        @Override
        public void onJumpToNavigator() {
/*
 * 设置途径点以及resetEndNode会回调该接口
 */

            for (Activity ac : activityList) {

                if (ac.getClass().getName().endsWith("BNDemoGuideActivity")) {

                    return;
                }
            }
            Intent intent = new Intent(context, BNDemoGuideActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ROUTE_PLAN_NODE, (BNRoutePlanNode) mBNRoutePlanNode);
            intent.putExtras(bundle);
            context.startActivity(intent);

        }

        @Override
        public void onRoutePlanFailed() {
            // TODO Auto-generated method stub
            Toast.makeText(context, "算路失败"+lat+"****"+lon+"*****"+latend+"******"+lngend+"-------------", Toast.LENGTH_SHORT).show();
        }
    }

    private void initSetting(){
        // 设置是否双屏显示
        BNaviSettingManager.setShowTotalRoadConditionBar(BNaviSettingManager.PreViewRoadCondition.ROAD_CONDITION_BAR_SHOW_ON);
        // 设置导航播报模式
        BNaviSettingManager.setVoiceMode(BNaviSettingManager.VoiceMode.Veteran);
        // 是否开启路况
        BNaviSettingManager.setRealRoadCondition(BNaviSettingManager.RealRoadCondition.NAVI_ITS_ON);
    }

    private BNOuterTTSPlayerCallback mTTSCallback = new BNOuterTTSPlayerCallback() {

        @Override
        public void stopTTS() {
            // TODO Auto-generated method stub
            Log.e("test_TTS", "stopTTS");
        }

        @Override
        public void resumeTTS() {
            // TODO Auto-generated method stub
            Log.e("test_TTS", "resumeTTS");
        }

        @Override
        public void releaseTTSPlayer() {
            // TODO Auto-generated method stub
            Log.e("test_TTS", "releaseTTSPlayer");
        }

        @Override
        public int playTTSText(String speech, int bPreempt) {
            // TODO Auto-generated method stub
            Log.e("test_TTS", "playTTSText" + "_" + speech + "_" + bPreempt);

            return 1;
        }

        @Override
        public void phoneHangUp() {
            // TODO Auto-generated method stub
            Log.e("test_TTS", "phoneHangUp");
        }

        @Override
        public void phoneCalling() {
            // TODO Auto-generated method stub
            Log.e("test_TTS", "phoneCalling");
        }

        @Override
        public void pauseTTS() {
            // TODO Auto-generated method stub
            Log.e("test_TTS", "pauseTTS");
        }

        @Override
        public void initTTSPlayer() {
            // TODO Auto-generated method stub
            Log.e("test_TTS", "initTTSPlayer");
        }

        @Override
        public int getTTSState() {
            // TODO Auto-generated method stub
            Log.e("test_TTS", "getTTSState");
            return 1;
        }
    };


}
