package com.zl.cn.jer.easycar.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.view.xlistview.XListView;

/**
 * Created by JER on 2016/9/2.
 */

public class DingDan_chongdian_fragment extends Fragment implements XListView.IXListViewListener {

    private View view;
    private XListView dingdanchongdianlv;
    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dingdan_chongdian, null);
        initialize();
        // 支持刷新
        dingdanchongdianlv.setPullRefreshEnable(true);
        // 能够加载
        dingdanchongdianlv.setPullLoadEnable(true);
        // 监听
        dingdanchongdianlv.setXListViewListener(this);
        return view;
    }

    private void initialize() {

        dingdanchongdianlv = (XListView) view.findViewById(R.id.dingdan_chongdian_lv);
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
        dingdanchongdianlv.stopLoadMore();
        dingdanchongdianlv.stopRefresh();
        dingdanchongdianlv.setRefreshTime("刚刚");
    }
}
