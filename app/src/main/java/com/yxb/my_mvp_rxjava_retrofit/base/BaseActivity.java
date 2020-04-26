package com.yxb.my_mvp_rxjava_retrofit.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yxb.my_mvp_rxjava_retrofit.utils.ActivityManager;

/**
 * Created by yuxibing on 2018/10/10.
 * 描述：
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    protected P presenter;
    public Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ActivityManager.getAppInstance().addActivity(this);
        presenter = initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getAppInstance().removeActivity(this);
        if (presenter != null) {
            presenter.detach();
            presenter = null;
        }
    }

    /**
     * 在子类中初始化对应的presenter
     *
     * @return 相应的presenter
     */
    public abstract P initPresenter();

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
