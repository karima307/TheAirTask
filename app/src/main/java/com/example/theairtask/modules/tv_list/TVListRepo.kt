package com.example.theairtask.modules.tv_list

import androidx.lifecycle.LiveData
import com.example.theairtask.data.remote.ApiConst
import com.example.theairtask.data.remote.ApiService
import com.nmg.baseinfrastructure.data.remote.ApiResponse
import com.nmg.baseinfrastructure.data.remote.ContextProviders
import com.nmg.baseinfrastructure.data.remote.NetworkBoundResource
import com.nmg.baseinfrastructure.data.remote.Resource

class TVListRepo(val apiService: ApiService, val contextProviders: ContextProviders) {
     fun getTVList(): LiveData<Resource<TVListResponse>> {
        return object : NetworkBoundResource<TVListResponse, TVListResponse>(contextProviders) {
            private var response: TVListResponse? = null
            override fun saveCallResult(item: TVListResponse) {
                response = item
            }

            override fun getResult(): TVListResponse? = response
            override fun createCall(): LiveData<ApiResponse<TVListResponse>> {
                return apiService.getTVList(ApiConst.TOKEN)
            }

            override fun shouldFetch(data: TVListResponse?): Boolean = true
            override fun loadFromDb(): LiveData<TVListResponse>? = null
        }.asLiveData()
    }
}