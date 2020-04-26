package com.yxb.my_mvp_rxjava_retrofit.retrofit;

import com.yxb.my_mvp_rxjava_retrofit.bean.TestBean;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by yuxibing on 2018/10/10.
 * 描述：
 */

public interface RetrofitService {
    String BASE_URL = "https://news-at.zhihu.com/api/4/";

    /*测试接口*/
    @GET("news/latest")
    Observable<TestBean> test();

    @Streaming
    @GET
    Call<ResponseBody> getImage(@Url String url);
}
