package me.teenyda.fruit.common.api;

import android.content.Intent;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.teenyda.fruit.common.entity.Bean;
import me.teenyda.fruit.common.entity.Book;
import me.teenyda.fruit.common.entity.Comments;
import me.teenyda.fruit.common.entity.Contact;
import me.teenyda.fruit.common.entity.Demo;
import me.teenyda.fruit.common.entity.Discounts;
import me.teenyda.fruit.common.entity.FileUploadResponse;
import me.teenyda.fruit.common.entity.Order;
import me.teenyda.fruit.common.entity.OrderItem;
import me.teenyda.fruit.common.entity.OrderPayment;
import me.teenyda.fruit.common.entity.Product;
import me.teenyda.fruit.common.entity.ProductCategory;
import me.teenyda.fruit.common.entity.SettlementOrder;
import me.teenyda.fruit.common.entity.SimpleProductEntity;
import me.teenyda.fruit.common.entity.User;
import me.teenyda.fruit.common.entity.Wallet;
import me.teenyda.fruit.common.net.request.OrderPaymentReq;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

import static me.teenyda.fruit.common.api.Constans.book;
import static me.teenyda.fruit.common.api.Constans.retrofit;

public interface ApiUrl {
    /**
     * 有效链接
     */
    @GET(retrofit)
    Call<Bean> getRetrofit();

    @GET(book)
    Observable<BaseResponse<Book>> getDemo();

    @GET("book/books")
    Observable<BaseResponse<List<Book>>> getDemoList();

    @PUT("fruit/book")
    Observable<BaseResponse<String>> updateBook(@Body Book book);

    @POST("fruit/book")
    Observable<BaseResponse<String>> addBook(@Body Book book);

    /**
     * 单个文件上传
     */
    @Multipart
    @POST("file/upload")
    Observable<BaseResponse<FileUploadResponse>> uploadImage(@Part MultipartBody.Part file,
                                                             @Part("file") RequestBody requestBody);

    /**
     * 多个文件上传
     */
    @Multipart
    @POST("file/multipleFiles")
    Observable<BaseResponse<List<FileUploadResponse>>> uploadImages(@Part List<MultipartBody.Part> files,
                                                             @Part("files") RequestBody requestBody);


    /**
     * 商品分类
     */
    @GET("fruit/category")
    Observable<BaseResponse<List<ProductCategory>>> category();

    /**
     * 根据分类获取商品
     */
    @GET("fruit/product/list/simple/category/{categoryId}")
    Observable<BaseResponse<List<SimpleProductEntity>>> productByCategory(@Path("categoryId") Integer categoryId);

    /**
     * 获取商品信息
     */
    @GET("v1/fruit/product/{productId}")
    Observable<BaseResponse<Product>> getProduct(@Path("productId") Integer productId);

    /**
     * 获取商品评论
     */
    @GET("fruit/comments/product/{productId}")
    Observable<BaseResponse<List<Comments>>> getComment(@Path("productId") Integer productId);
    /**
     * 获取最佳评论
     */
    @GET("fruit/comments/product/{productId}/best")
    Observable<BaseResponse<Comments>> getBestComment(@Path("productId") Integer productId);

    /**
     * 下订单
     * @param order
     * @return
     */
    @POST("fruit/order/buy")
    Observable<BaseResponse<OrderItem>> order(@Body OrderItem order);

    /**
     * 下订单
     * @param orderNum
     * @return
     */
    @GET("fruit/order/{orderNum}")
    Observable<BaseResponse<SettlementOrder>> getOrder(@Path("orderNum") String orderNum);

    /**
     * 获取所以订单
     * @param userId
     * @return
     */
    @GET("fruit/order/item/{userId}")
    Observable<BaseResponse<List<Order>>> getOrders(@Path("userId") Integer userId);

    @GET("order/item/{userId}/status/{status}")
    Observable<BaseResponse<List<Order>>> getOrdersByStatus(@Path("userId") Integer userId,
                                                            @Path("status") Integer status);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @GET("fruit/user/{userId}")
    Observable<BaseResponse<User>> getUser(@Path("userId") Integer userId);

    /**
     * 获取用户优惠
     * @return
     */
    @GET("fruit/discounts/user")
    Observable<BaseResponse<List<Discounts>>> getUserDiscounts();

    /**
     * 获取会员优惠
     * @return
     */
    @GET("fruit/discounts/member")
    Observable<BaseResponse<List<Discounts>>> getMemberDiscounts();


    /**
     * 获取钱包
     * @return
     */
    @GET("fruit/wallet/{userId}")
    Observable<BaseResponse<Wallet>> getWallet(@Path("userId") Integer userId);

    /**
     * 去支付订单
     * @param req
     * @return
     */
    @POST("fruit/order/payment")
    Observable<BaseResponse<OrderPayment>> toPayment(@Body OrderPaymentReq req);

    /**
     * 支付
     * @param orderNum
     * @return
     */
    @PUT("fruit/order/payment/pay/{orderNum}")
    Observable<BaseResponse<OrderPayment>> pay(@Path("orderNum") String orderNum);

    /**
     * 支付超时，结束订单
     * @param orderNum
     * @return
     */
    @POST("fruit/order/payment/finish/{orderNum}")
    Observable<BaseResponse<Boolean>> finish(@Path("orderNum") String orderNum);

    /**
     * 获取支付订单
     * @param orderNum 订单编号
     * @return
     */
    @GET("fruit/order/payment/{orderNum}")
    Observable<BaseResponse<OrderPayment>> getOrderPayment(@Path("orderNum") String orderNum);

    /**
     * 获取联系人
     * @param userId
     * @return
     */
    @GET("fruit/contact/{userId}")
    Observable<BaseResponse<List<Contact>>> getContacts(@Path("userId") Integer userId);

    /**
     * 添加联系人
     * @param contact
     * @return
     */
    @POST("fruit/contact")
    Observable<BaseResponse<Contact>> addContacts(@Body Contact contact);

    /**
     * 添加联系人
     * @param contactId
     * @return
     */
    @DELETE("fruit/contact/{contactId}")
    Observable<BaseResponse<Boolean>> deleteContacts(@Path("contactId") Integer contactId);

    /**
     * TODO Get请求
     */
    //第一种方式：GET不带参数
   /* @GET("retrofit.txt")
    Observable<BaseResponse<Demo>> getUser();
    @GET
    Observable<Demo> getUser(@Url String url);
    @GET
    Observable<Demo> getUser1(@Url String url); //简洁方式   直接获取所需数据
    //第二种方式：GET带参数
    @GET("api/data/{type}/{count}/{page}")
    Observable<Demo> getUser(@Path("type") String type, @Path("count") int count, @Path("page") int page);
    //第三种方式：GET带请求参数：https://api.github.com/users/whatever?client_id=xxxx&client_secret=yyyy
    @GET("users/whatever")
    Observable<Demo> getUser(@Query("client_id") String id, @Query("client_secret") String secret);
    @GET("users/whatever")
    Observable<Demo> getUser(@QueryMap Map<String, String> info);*/

    /**
     * TODO POST请求
     */
    //第一种方式：@Body
    @Headers("Accept:application/json")
    @POST("login")
    Observable<Demo> postUser(@Body RequestBody body);
    //第二种方式：@Field

    @Headers("Accept:application/json")
    @POST("auth/login")
    @FormUrlEncoded
    Observable<Demo> postUser(@Field("username") String username, @Field("password") String password);
    //多个参数
    Observable<Demo> postUser(@FieldMap Map<String, String> map);

    /**
     * TODO DELETE
     */
    @DELETE("member_follow_member/{id}")
    Observable<Demo> delete(@Header("Authorization") String auth, @Path("id") int id);

    /**
     * TODO PUT
     */
    @PUT("member")
    Observable<Demo> put(@HeaderMap Map<String, String> headers,
                         @Query("nickname") String nickname);

    /**
     * TODO 文件上传
     */
    @Multipart
    @POST("upload")
    Observable<ResponseBody> upload(@Part("description") RequestBody description, @Part MultipartBody.Part file);

    //亲测可用
    @Multipart
    @POST("member/avatar")
    Observable<Demo> uploadImage(@HeaderMap Map<String, String> headers, @Part MultipartBody.Part file);

    /**
     * 多文件上传
     */
    @Multipart
    @POST("register")
    Observable<ResponseBody> upload(@PartMap Map<String, RequestBody> params, @Part("description") RequestBody description);
    //Observable<ResponseBody> upload(@Part() List<MultipartBody.Part> parts);

    @Multipart
    @POST("member/avatar")
    Observable<Demo> uploadImage1(@HeaderMap Map<String, String> headers, @Part List<MultipartBody.Part> file);

    /**
     * 来自https://blog.csdn.net/impure/article/details/79658098
     * @Streaming 这个注解必须添加，否则文件全部写入内存，文件过大会造成内存溢出
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Header("RANGE") String start, @Url String url);
}
