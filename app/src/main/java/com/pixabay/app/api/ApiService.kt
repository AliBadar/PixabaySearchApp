package com.pixabay.app.api

import com.pixabay.app.models.Pixaby
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api")
    fun searchData(@Query("key") key:String, @Query("q") q:String, @Query("image_type") image_type:String, @Query("pretty") pretty:Boolean): Single<Pixaby>

}