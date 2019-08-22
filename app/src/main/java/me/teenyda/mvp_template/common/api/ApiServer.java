package me.teenyda.mvp_template.common.api;

import io.reactivex.Observable;
import me.teenyda.mvp_template.common.net.resp.BaseResponse;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public interface ApiServer {

    @POST("login")
    Observable<BaseResponse> login(@Field("username")String username,
                                   @Field("password")String password);
}
