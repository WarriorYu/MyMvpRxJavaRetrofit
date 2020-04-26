package com.yxb.my_mvp_rxjava_retrofit.retrofit;

/**
 * Created by yuxibing on 2018/10/10.
 * 描述：
 */

public class Api extends BaseApiImpl {
    private static Api api = new Api(RetrofitService.BASE_URL);

    public Api(String baseUrl) {
        super(baseUrl);
    }

    public static RetrofitService getInstance() {
        return api.getRetrofit().create(RetrofitService.class);
    }
}
