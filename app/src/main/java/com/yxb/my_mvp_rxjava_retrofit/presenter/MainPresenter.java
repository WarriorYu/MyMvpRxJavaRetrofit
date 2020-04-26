package com.yxb.my_mvp_rxjava_retrofit.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yxb.my_mvp_rxjava_retrofit.base.BasePresenterImpl;
import com.yxb.my_mvp_rxjava_retrofit.bean.TestBean;
import com.yxb.my_mvp_rxjava_retrofit.contract.MainContract;
import com.yxb.my_mvp_rxjava_retrofit.retrofit.Api;
import com.yxb.my_mvp_rxjava_retrofit.utils.FilePathUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yuxibing on 2018/10/10.
 * 描述：
 */

public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {
    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        super(view);
        this.mView = view;
    }

    @Override
    public void getData() {
        Api.getInstance().test()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                        view.showLoadingDialog("开始刷新");
                    }
                })
                .map(new Function<TestBean, List<TestBean.StoriesBean>>() {

                    @Override
                    public List<TestBean.StoriesBean> apply(@NonNull TestBean testBean) throws Exception {
                        return testBean.getStories();
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TestBean.StoriesBean>>() {
                    @Override
                    public void accept(@NonNull List<TestBean.StoriesBean> storiesBeans) throws Exception {
                        view.dismissLoadingDialog();
                        view.setData(storiesBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        view.dismissLoadingDialog();
                        Log.e("Tag", throwable.getMessage());
                    }
                });
    }

    @Override
    public void loadImage() {
        Api.getInstance().getImage("http://img.guanfumuseum.org.cn/gf-images-1566452157739.jpg").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            InputStream inputStream = response.body().byteStream();
                            String imagePath = FilePathUtil.getDefaultImagePath((Context) mView);
                            String fileName = new Date().getTime() + ".jpg";
                            File file = new File(imagePath, fileName);
                            boolean b = FileIOUtils.writeFileFromIS(file, inputStream);
                            if (b) {
                                ((Context) mView).sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
                                LogUtils.e("下载成功");
                                ToastUtils.showShort("下载成功");
                            }
                        }
                    }).start();

                } else {
                    LogUtils.e("下载失败");
                    ToastUtils.showShort("下载失败");

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtils.e("下载失败");
                ToastUtils.showShort("下载失败");

            }
        });
    }
}
