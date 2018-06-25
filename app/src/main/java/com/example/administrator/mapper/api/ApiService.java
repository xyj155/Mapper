package com.example.administrator.mapper.api;

import com.example.administrator.mapper.entity.Usage;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/25.
 */

public interface ApiService {

    public static final String Base_URL = "http://182.254.147.87/";
    /**
     *普通写法
     */
    @GET("/Usage/public/index.php/index/Usage/getUsageList")
    Observable<Usage> getUsageList();

//    @GET("{url}")
//    Observable<ResponseBody> executeGet(
//            @Path("url") String url,
//            @QueryMap Map<String, String> maps);
//
//
//    @POST("{url}")
//    Observable<ResponseBody> executePost(
//            @Path("url") String url,
//            @QueryMap Map<String, String> maps);
//
//    @Multipart
//    @POST("{url}")
//    Observable<ResponseBody> upLoadFile(
//            @Path("url") String url,
//            @Part("image\\\\"; filename=\\"image.jpg") RequestBody avatar);
//
//
//    @POST("{url}")
//    Observable<ResponseBody> uploadFiles(
//            @Path("url") String url,
//            @Path("headers") Map<String, String> headers,
//            @Part("filename") String description,
//            @PartMap()  Map<String, RequestBody> maps);
//
//    @Streaming
//    @GET
//    Observable<ResponseBody> downloadFile(@Url String fileUrl);

}