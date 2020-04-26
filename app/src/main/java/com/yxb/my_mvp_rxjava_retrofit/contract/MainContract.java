package com.yxb.my_mvp_rxjava_retrofit.contract;

import com.yxb.my_mvp_rxjava_retrofit.base.BasePresenter;
import com.yxb.my_mvp_rxjava_retrofit.base.BaseView;
import com.yxb.my_mvp_rxjava_retrofit.bean.TestBean;

import java.util.List;

/**
 * Created by yuxibing on 2018/10/10.
 * 描述：
 */

public interface MainContract {
    interface View extends BaseView {
        /**
         * 设置数据
         */
        void setData(List<TestBean.StoriesBean> dataList);
    }

    interface Presenter extends BasePresenter {
        /**
         * 获取数据
         */
        void getData();

        void loadImage();
    }
}
