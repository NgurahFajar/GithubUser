package com.ngurah.githubuser.api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>


    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(@Path("username") username: String): Call<ArrayList<ItemsItem>>
    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<ItemsItem>>
}