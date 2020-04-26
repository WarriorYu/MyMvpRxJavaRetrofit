package com.yxb.my_mvp_rxjava_retrofit.activity;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.yxb.my_mvp_rxjava_retrofit.R;
import com.yxb.my_mvp_rxjava_retrofit.TestAdapter;
import com.yxb.my_mvp_rxjava_retrofit.base.BaseActivity;
import com.yxb.my_mvp_rxjava_retrofit.bean.TestBean;
import com.yxb.my_mvp_rxjava_retrofit.contract.MainContract;
import com.yxb.my_mvp_rxjava_retrofit.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {
    private TestAdapter adapter;
    private List<TestBean.StoriesBean> list = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        presenter.getData();
        Looper.loop();
    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TestAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListeter(new TestAdapter.onItemClickListeter() {
            @Override
            public void onClick() {

                adapter.notifyItemRangeChanged(0, 2, 3);

                //下载原图
                //presenter.loadImage();
//            startActivity(new Intent(MainActivity.this,SecondActivity.class));
//                startActivity(new Intent(MainActivity.this,ThreeeActivity.class));

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void setData(List<TestBean.StoriesBean> dataList) {
        list.addAll(dataList);
        adapter.notifyDataSetChanged();
    }
}
