package com.example.fakeapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderAPI {

    @GET("photos")
    Call<List<Post>> getPosts(
            @Query("userId") Integer userId

    );




}
