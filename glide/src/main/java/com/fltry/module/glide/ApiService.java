package com.fltry.module.glide;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/v2/movie/top250")
    Observable<Movice> getMovice(@Query("count") int count);

}
