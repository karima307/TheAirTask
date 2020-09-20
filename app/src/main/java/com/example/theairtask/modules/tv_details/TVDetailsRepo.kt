package com.example.theairtask.modules.tv_details

import androidx.lifecycle.LiveData
import com.example.theairtask.data.remote.ApiConst
import com.example.theairtask.data.remote.ApiService
import com.example.theairtask.modules.tv_details.rate.SubmitRateReasponse
import com.example.theairtask.modules.tv_list.TVListResponse
import com.nmg.baseinfrastructure.data.remote.ApiResponse
import com.nmg.baseinfrastructure.data.remote.ContextProviders
import com.nmg.baseinfrastructure.data.remote.NetworkBoundResource
import com.nmg.baseinfrastructure.data.remote.Resource

class TVDetailsRepo (val apiService: ApiService, val contextProviders: ContextProviders) {
    fun getTVDtails(tvId:Int): LiveData<Resource<TVDetailsResponse>> {
        return object : NetworkBoundResource<TVDetailsResponse, TVDetailsResponse>(contextProviders) {
            private var response: TVDetailsResponse? = null
            override fun saveCallResult(item: TVDetailsResponse) {
                response = item
            }

            override fun getResult(): TVDetailsResponse? = response
            override fun createCall(): LiveData<ApiResponse<TVDetailsResponse>> {
                return apiService.getTVListDetails(tvId,ApiConst.TOKEN)
            }

            override fun shouldFetch(data: TVDetailsResponse?): Boolean = true
            override fun loadFromDb(): LiveData<TVDetailsResponse>? = null
        }.asLiveData()
    }

    fun submitRate(tvId:Int , sessionID:String , rate:Double): LiveData<Resource<SubmitRateReasponse>> {

        val rateMap = HashMap<String,Any>()
        rateMap["value"]= rate
        return object : NetworkBoundResource<SubmitRateReasponse, SubmitRateReasponse>(contextProviders) {
            private var response: SubmitRateReasponse? = null
            override fun saveCallResult(item: SubmitRateReasponse) {
                response = item
            }

            override fun getResult(): SubmitRateReasponse? = response
            override fun createCall(): LiveData<ApiResponse<SubmitRateReasponse>> {
                return apiService.submitRate(tvId,rateMap,sessionID, ApiConst.TOKEN)
            }

            override fun shouldFetch(data: SubmitRateReasponse?): Boolean = true
            override fun loadFromDb(): LiveData<SubmitRateReasponse>? = null
        }.asLiveData()
    }
}