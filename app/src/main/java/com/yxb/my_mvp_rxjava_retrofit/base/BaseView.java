package com.yxb.my_mvp_rxjava_retrofit.base;

/**
 * Created by yuxibing on 2018/10/10.
 * 描述：
 */

public interface BaseView {
    void showLoadingDialog(String msg);

    void dismissLoadingDialog();
}
