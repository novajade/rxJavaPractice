package com.example.zzong.rxjavapractice.Retrofit;

import com.example.zzong.rxjavapractice.Model.MovieData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface NaverInterface {
    @GET("/v1/search/movie.json")
    Call<MovieData> getMovieData(
            @Header("X-Naver-Client-Id") String id,
            @Header("X-Naver-Client-Secret") String secret,
            @Query("query") String query);

}
