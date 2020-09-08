package me.teenyda.fruit_store.common.api;

import java.util.List;

import io.reactivex.Observable;
import me.teenyda.fruit_store.common.entity.Book;
import me.teenyda.fruit_store.common.net.resp.BaseResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * author: teenyda
 * date: 2019/8/21
 * description:
 */
public interface ApiServer {

    @FormUrlEncoded
    @POST("login")
    Observable<BaseResponse> login(@Field("username")String username,
                                   @Field("password")String password);

    @GET("book/book")
    Observable<Book> book();

    @GET("book/books")
    Observable<List<Book>> books();

    @Multipart
    @POST("file/upload")
    Observable<BaseResponse> uploadImage(@Part MultipartBody.Part file,
                                         @Part("file") RequestBody requestBody);
}
