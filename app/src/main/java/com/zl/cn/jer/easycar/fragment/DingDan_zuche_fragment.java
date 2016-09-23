package com.zl.cn.jer.easycar.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.view.xlistview.XListView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by JER on 2016/9/2.
 */

public class DingDan_zuche_fragment extends Fragment implements XListView.IXListViewListener {

    private View view;
    private XListView dingdanzuchelv;
    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dingdan_zuche,null);
        initialize();
        // 支持刷新
        dingdanzuchelv.setPullRefreshEnable(true);
        // 能够加载
        dingdanzuchelv.setPullLoadEnable(true);
        // 监听
        dingdanzuchelv.setXListViewListener(this);



        return view;
    }
    /**
     *
     * 取tooken值和id
     * 示范ybb
     *
     * */



    private void initialize() {

        dingdanzuchelv = (XListView)view.findViewById(R.id.dingdan_zuche_lv);
    }
    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                load();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                load();
            }
        }, 2000);
    }

    public void load() {
        dingdanzuchelv.stopLoadMore();
        dingdanzuchelv.stopRefresh();
        dingdanzuchelv.setRefreshTime("刚刚");
    }
}
