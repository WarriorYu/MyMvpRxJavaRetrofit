package com.yxb.my_mvp_rxjava_retrofit.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by yuxibing on 2018/10/10.
 * 描述：
 */

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {
    protected V view;

    public BasePresenterImpl(V view) {
        this.view = view;
        start();
    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {
        this.view = null;
        unDisposable();
    }

    //将所有正在处理的Subscription都添加到CompositeSubscription中。统一退出的时候注销观察
    private CompositeDisposable mCompositeDisposable;

    /**
     * 将Disposable添加
     *
     * @param subscription
     */
    @Override
    public void addDisposable(Disposable subscription) {
        //mCompositeDisposable 如果解绑了的话添加 subscription 需要新的实例否则绑定时无效的
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 在界面退出等需要解绑观察者的情况下调用此方法统一解绑，防止Rx造成的内存泄漏
     */
    @Override
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
