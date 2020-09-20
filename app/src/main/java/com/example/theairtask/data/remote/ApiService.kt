package com.example.theairtask.data.remote

import androidx.lifecycle.LiveData
import com.example.theairtask.modules.session.SessionResponse
import com.example.theairtask.modules.tv_details.TVDetailsResponse
import com.example.theairtask.modules.tv_details.rate.SubmitRateReasponse
import com.example.theairtask.modules.tv_list.TVListResponse
import com.nmg.baseinfrastructure.data.remote.ApiResponse
import retrofit2.http.*

interface ApiService {
    @GET("3/tv/top_rated?")
    fun getTVList(@Query("api_key") api_key: String): LiveData<ApiResponse<TVListResponse>>

    @GET("3/tv/{tv_id}")
    fun getTVListDetails(@Path("tv_id") tv_id: Int,@Query("api_key") api_key: String): LiveData<ApiResponse<TVDetailsResponse>>
    @GET("3/authentication/guest_session/new")
    fun getGuestSessionId(@Query("api_key") api_key: String): LiveData<ApiResponse<SessionResponse>>
    @POST("3/tv/{tv_id}/rating")
    fun submitRate(
        @Path("tv_id") tv_id: Int,
        @Body rate: HashMap<String, Any>,
        @Query("guest_session_id") guest_session_id:String,@Query("api_key") api_key: String
    ): LiveData<ApiResponse<SubmitRateReasponse>>
}