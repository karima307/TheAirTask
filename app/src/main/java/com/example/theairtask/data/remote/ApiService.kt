package com.example.theairtask.data.remote

import androidx.lifecycle.LiveData
import com.example.theairtask.modules.tv_list.TVListResponse
import com.nmg.baseinfrastructure.data.remote.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/tv/top_rated?")
    fun getTVList(@Query("api_key") api_key: String): LiveData<ApiResponse<TVListResponse>>

    @GET("3/tv/100")
    fun getTVListDetails(@Query("api_key") api_key: String,tvId:Int): LiveData<ApiResponse<TVListResponse>>


}